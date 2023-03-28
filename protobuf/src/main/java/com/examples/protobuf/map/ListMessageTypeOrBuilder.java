// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: map.proto

package com.examples.protobuf.map;

public interface ListMessageTypeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ref.parse.ListMessageType)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated bool boolValue = 1;</code>
   * @return A list containing the boolValue.
   */
  java.util.List<java.lang.Boolean> getBoolValueList();
  /**
   * <code>repeated bool boolValue = 1;</code>
   * @return The count of boolValue.
   */
  int getBoolValueCount();
  /**
   * <code>repeated bool boolValue = 1;</code>
   * @param index The index of the element to return.
   * @return The boolValue at the given index.
   */
  boolean getBoolValue(int index);

  /**
   * <code>repeated string stringValue = 2;</code>
   * @return A list containing the stringValue.
   */
  java.util.List<java.lang.String>
      getStringValueList();
  /**
   * <code>repeated string stringValue = 2;</code>
   * @return The count of stringValue.
   */
  int getStringValueCount();
  /**
   * <code>repeated string stringValue = 2;</code>
   * @param index The index of the element to return.
   * @return The stringValue at the given index.
   */
  java.lang.String getStringValue(int index);
  /**
   * <code>repeated string stringValue = 2;</code>
   * @param index The index of the value to return.
   * @return The bytes of the stringValue at the given index.
   */
  com.google.protobuf.ByteString
      getStringValueBytes(int index);

  /**
   * <code>repeated bytes bytesValue = 3;</code>
   * @return A list containing the bytesValue.
   */
  java.util.List<com.google.protobuf.ByteString> getBytesValueList();
  /**
   * <code>repeated bytes bytesValue = 3;</code>
   * @return The count of bytesValue.
   */
  int getBytesValueCount();
  /**
   * <code>repeated bytes bytesValue = 3;</code>
   * @param index The index of the element to return.
   * @return The bytesValue at the given index.
   */
  com.google.protobuf.ByteString getBytesValue(int index);

  /**
   * <code>repeated int32 int32Value = 4;</code>
   * @return A list containing the int32Value.
   */
  java.util.List<java.lang.Integer> getInt32ValueList();
  /**
   * <code>repeated int32 int32Value = 4;</code>
   * @return The count of int32Value.
   */
  int getInt32ValueCount();
  /**
   * <code>repeated int32 int32Value = 4;</code>
   * @param index The index of the element to return.
   * @return The int32Value at the given index.
   */
  int getInt32Value(int index);

  /**
   * <code>repeated fixed32 fixed32Value = 5;</code>
   * @return A list containing the fixed32Value.
   */
  java.util.List<java.lang.Integer> getFixed32ValueList();
  /**
   * <code>repeated fixed32 fixed32Value = 5;</code>
   * @return The count of fixed32Value.
   */
  int getFixed32ValueCount();
  /**
   * <code>repeated fixed32 fixed32Value = 5;</code>
   * @param index The index of the element to return.
   * @return The fixed32Value at the given index.
   */
  int getFixed32Value(int index);

  /**
   * <code>repeated uint32 uint32Value = 6;</code>
   * @return A list containing the uint32Value.
   */
  java.util.List<java.lang.Integer> getUint32ValueList();
  /**
   * <code>repeated uint32 uint32Value = 6;</code>
   * @return The count of uint32Value.
   */
  int getUint32ValueCount();
  /**
   * <code>repeated uint32 uint32Value = 6;</code>
   * @param index The index of the element to return.
   * @return The uint32Value at the given index.
   */
  int getUint32Value(int index);

  /**
   * <code>repeated int64 int64Value = 7;</code>
   * @return A list containing the int64Value.
   */
  java.util.List<java.lang.Long> getInt64ValueList();
  /**
   * <code>repeated int64 int64Value = 7;</code>
   * @return The count of int64Value.
   */
  int getInt64ValueCount();
  /**
   * <code>repeated int64 int64Value = 7;</code>
   * @param index The index of the element to return.
   * @return The int64Value at the given index.
   */
  long getInt64Value(int index);

  /**
   * <code>repeated fixed64 fixed64Value = 8;</code>
   * @return A list containing the fixed64Value.
   */
  java.util.List<java.lang.Long> getFixed64ValueList();
  /**
   * <code>repeated fixed64 fixed64Value = 8;</code>
   * @return The count of fixed64Value.
   */
  int getFixed64ValueCount();
  /**
   * <code>repeated fixed64 fixed64Value = 8;</code>
   * @param index The index of the element to return.
   * @return The fixed64Value at the given index.
   */
  long getFixed64Value(int index);

  /**
   * <code>repeated uint64 uint64Value = 9;</code>
   * @return A list containing the uint64Value.
   */
  java.util.List<java.lang.Long> getUint64ValueList();
  /**
   * <code>repeated uint64 uint64Value = 9;</code>
   * @return The count of uint64Value.
   */
  int getUint64ValueCount();
  /**
   * <code>repeated uint64 uint64Value = 9;</code>
   * @param index The index of the element to return.
   * @return The uint64Value at the given index.
   */
  long getUint64Value(int index);

  /**
   * <code>repeated float floatValue = 10;</code>
   * @return A list containing the floatValue.
   */
  java.util.List<java.lang.Float> getFloatValueList();
  /**
   * <code>repeated float floatValue = 10;</code>
   * @return The count of floatValue.
   */
  int getFloatValueCount();
  /**
   * <code>repeated float floatValue = 10;</code>
   * @param index The index of the element to return.
   * @return The floatValue at the given index.
   */
  float getFloatValue(int index);

  /**
   * <code>repeated double doubleValue = 11;</code>
   * @return A list containing the doubleValue.
   */
  java.util.List<java.lang.Double> getDoubleValueList();
  /**
   * <code>repeated double doubleValue = 11;</code>
   * @return The count of doubleValue.
   */
  int getDoubleValueCount();
  /**
   * <code>repeated double doubleValue = 11;</code>
   * @param index The index of the element to return.
   * @return The doubleValue at the given index.
   */
  double getDoubleValue(int index);

  /**
   * <code>repeated .ref.parse.EnumType enumValue = 12;</code>
   * @return A list containing the enumValue.
   */
  java.util.List<com.examples.protobuf.map.EnumType> getEnumValueList();
  /**
   * <code>repeated .ref.parse.EnumType enumValue = 12;</code>
   * @return The count of enumValue.
   */
  int getEnumValueCount();
  /**
   * <code>repeated .ref.parse.EnumType enumValue = 12;</code>
   * @param index The index of the element to return.
   * @return The enumValue at the given index.
   */
  com.examples.protobuf.map.EnumType getEnumValue(int index);
  /**
   * <code>repeated .ref.parse.EnumType enumValue = 12;</code>
   * @return A list containing the enum numeric values on the wire for enumValue.
   */
  java.util.List<java.lang.Integer>
  getEnumValueValueList();
  /**
   * <code>repeated .ref.parse.EnumType enumValue = 12;</code>
   * @param index The index of the value to return.
   * @return The enum numeric value on the wire of enumValue at the given index.
   */
  int getEnumValueValue(int index);

  /**
   * <code>repeated .ref.parse.ReferenceMessageType referenceValue = 13;</code>
   */
  java.util.List<com.examples.protobuf.map.ReferenceMessageType> 
      getReferenceValueList();
  /**
   * <code>repeated .ref.parse.ReferenceMessageType referenceValue = 13;</code>
   */
  com.examples.protobuf.map.ReferenceMessageType getReferenceValue(int index);
  /**
   * <code>repeated .ref.parse.ReferenceMessageType referenceValue = 13;</code>
   */
  int getReferenceValueCount();
  /**
   * <code>repeated .ref.parse.ReferenceMessageType referenceValue = 13;</code>
   */
  java.util.List<? extends com.examples.protobuf.map.ReferenceMessageTypeOrBuilder> 
      getReferenceValueOrBuilderList();
  /**
   * <code>repeated .ref.parse.ReferenceMessageType referenceValue = 13;</code>
   */
  com.examples.protobuf.map.ReferenceMessageTypeOrBuilder getReferenceValueOrBuilder(
      int index);
}
