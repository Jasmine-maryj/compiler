package com.dev;

import java.util.ArrayList;
import java.util.List;

public abstract class ParserRuleContext implements ParseTree {

  private ParseTree parent;
  private List<ParseTree> children;

  @Override
  public ParseTree getParent() {
    return parent;
  }

  @Override
  public void setParent(ParseTree parent) {
    this.parent = parent;
  }

  @Override
  public Object getPayload() {
    return this;
  }

  public void addChild(ParseTree child) {
    child.setParent(this);
    if (children == null) {
      children = new ArrayList<>();
    }
    children.add(child);
  }

  @Override
  public ParseTree getChild(int i) {
    return children.get(i);
  }

  @Override
  public int getChildCount() {
    return (children != null) ? children.size() : 0;
  }

  public abstract <T> T accept(Visitor<? extends T> visitor);
}

