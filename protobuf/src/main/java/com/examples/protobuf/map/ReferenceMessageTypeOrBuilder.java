// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: map.proto

package com.examples.protobuf.map;

public interface ReferenceMessageTypeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ref.parse.ReferenceMessageType)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string string = 1;</code>
   * @return The string.
   */
  java.lang.String getString();
  /**
   * <code>string string = 1;</code>
   * @return The bytes for string.
   */
  com.google.protobuf.ByteString
      getStringBytes();

  /**
   * <code>repeated string list = 2;</code>
   * @return A list containing the list.
   */
  java.util.List<java.lang.String>
      getListList();
  /**
   * <code>repeated string list = 2;</code>
   * @return The count of list.
   */
  int getListCount();
  /**
   * <code>repeated string list = 2;</code>
   * @param index The index of the element to return.
   * @return The list at the given index.
   */
  java.lang.String getList(int index);
  /**
   * <code>repeated string list = 2;</code>
   * @param index The index of the value to return.
   * @return The bytes of the list at the given index.
   */
  com.google.protobuf.ByteString
      getListBytes(int index);

  /**
   * <code>map&lt;string, string&gt; map = 3;</code>
   */
  int getMapCount();
  /**
   * <code>map&lt;string, string&gt; map = 3;</code>
   */
  boolean containsMap(
      java.lang.String key);
  /**
   * Use {@link #getMapMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getMap();
  /**
   * <code>map&lt;string, string&gt; map = 3;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getMapMap();
  /**
   * <code>map&lt;string, string&gt; map = 3;</code>
   */

  java.lang.String getMapOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;string, string&gt; map = 3;</code>
   */

  java.lang.String getMapOrThrow(
      java.lang.String key);
}