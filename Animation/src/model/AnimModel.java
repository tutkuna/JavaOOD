package model;

import java.awt.Dimension;
import java.util.ArrayList;

import shapes.ReadShape;

/**
 * <title>model, like, Animation-Model.</title>
 * READ ONLY! This interface represents the operations offered by the animator model. One object of
 * the model represents all the shapes in an animation. The model is immutable after creation
 * through its builder.
 */
public interface AnimModel {

  /**
   * Returns a list of all the shapes stored in the model.
   *
   * @return a copy of the list of all the shapes stored in the model.
   */
  ArrayList<ReadShape> readAll();

  /**
   * Return an ordered list copy of the set of all ids stored.
   *
   * @return a list copy of the set of all ids stored
   */
  ArrayList<String> getIDs();

  /**
   * Return the shape of given id as a read only.
   *
   * @param id of the shape to be returned
   * @return the shape as a read only
   */
  ReadShape getShape(String id);

  /**
   * Return the bound stored in the model, for the given bound type.
   *
   * @param s the bound type to be returned
   * @return the bound for the bound type given
   * @throws IllegalArgumentException if given bound type not found
   */
  int getBounds(String s) throws IllegalArgumentException;

  /**
   * Return the maximum dimension this anim spans.
   *
   * @return the maximum dimension this anim spans
   */
  Dimension getMax();
}
