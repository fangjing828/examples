// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: parse.proto

package com.examples.protobuf.parse;

/**
 * Protobuf type {@code ref.parse.SimpleNotifyWithoutMiddleFields}
 */
public final class SimpleNotifyWithoutMiddleFields extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ref.parse.SimpleNotifyWithoutMiddleFields)
    SimpleNotifyWithoutMiddleFieldsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SimpleNotifyWithoutMiddleFields.newBuilder() to construct.
  private SimpleNotifyWithoutMiddleFields(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SimpleNotifyWithoutMiddleFields() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new SimpleNotifyWithoutMiddleFields();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SimpleNotifyWithoutMiddleFields(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            userId_ = input.readInt64();
            break;
          }
          case 16: {

            accountId_ = input.readInt64();
            break;
          }
          case 32: {

            sendTime_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.examples.protobuf.parse.Parse.internal_static_ref_parse_SimpleNotifyWithoutMiddleFields_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.examples.protobuf.parse.Parse.internal_static_ref_parse_SimpleNotifyWithoutMiddleFields_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields.class, com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields.Builder.class);
  }

  public static final int USERID_FIELD_NUMBER = 1;
  private long userId_;
  /**
   * <code>int64 userId = 1;</code>
   * @return The userId.
   */
  @java.lang.Override
  public long getUserId() {
    return userId_;
  }

  public static final int ACCOUNTID_FIELD_NUMBER = 2;
  private long accountId_;
  /**
   * <code>int64 accountId = 2;</code>
   * @return The accountId.
   */
  @java.lang.Override
  public long getAccountId() {
    return accountId_;
  }

  public static final int SENDTIME_FIELD_NUMBER = 4;
  private long sendTime_;
  /**
   * <code>int64 sendTime = 4;</code>
   * @return The sendTime.
   */
  @java.lang.Override
  public long getSendTime() {
    return sendTime_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (userId_ != 0L) {
      output.writeInt64(1, userId_);
    }
    if (accountId_ != 0L) {
      output.writeInt64(2, accountId_);
    }
    if (sendTime_ != 0L) {
      output.writeInt64(4, sendTime_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (userId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, userId_);
    }
    if (accountId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, accountId_);
    }
    if (sendTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, sendTime_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields)) {
      return super.equals(obj);
    }
    com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields other = (com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields) obj;

    if (getUserId()
        != other.getUserId()) return false;
    if (getAccountId()
        != other.getAccountId()) return false;
    if (getSendTime()
        != other.getSendTime()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + USERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUserId());
    hash = (37 * hash) + ACCOUNTID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAccountId());
    hash = (37 * hash) + SENDTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getSendTime());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ref.parse.SimpleNotifyWithoutMiddleFields}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ref.parse.SimpleNotifyWithoutMiddleFields)
      com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFieldsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_SimpleNotifyWithoutMiddleFields_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_SimpleNotifyWithoutMiddleFields_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields.class, com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields.Builder.class);
    }

    // Construct using com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      userId_ = 0L;

      accountId_ = 0L;

      sendTime_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_SimpleNotifyWithoutMiddleFields_descriptor;
    }

    @java.lang.Override
    public com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields getDefaultInstanceForType() {
      return com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields.getDefaultInstance();
    }

    @java.lang.Override
    public com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields build() {
      com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields buildPartial() {
      com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields result = new com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields(this);
      result.userId_ = userId_;
      result.accountId_ = accountId_;
      result.sendTime_ = sendTime_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields) {
        return mergeFrom((com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields other) {
      if (other == com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields.getDefaultInstance()) return this;
      if (other.getUserId() != 0L) {
        setUserId(other.getUserId());
      }
      if (other.getAccountId() != 0L) {
        setAccountId(other.getAccountId());
      }
      if (other.getSendTime() != 0L) {
        setSendTime(other.getSendTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long userId_ ;
    /**
     * <code>int64 userId = 1;</code>
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
      return userId_;
    }
    /**
     * <code>int64 userId = 1;</code>
     * @param value The userId to set.
     * @return This builder for chaining.
     */
    public Builder setUserId(long value) {
      
      userId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 userId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserId() {
      
      userId_ = 0L;
      onChanged();
      return this;
    }

    private long accountId_ ;
    /**
     * <code>int64 accountId = 2;</code>
     * @return The accountId.
     */
    @java.lang.Override
    public long getAccountId() {
      return accountId_;
    }
    /**
     * <code>int64 accountId = 2;</code>
     * @param value The accountId to set.
     * @return This builder for chaining.
     */
    public Builder setAccountId(long value) {
      
      accountId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 accountId = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAccountId() {
      
      accountId_ = 0L;
      onChanged();
      return this;
    }

    private long sendTime_ ;
    /**
     * <code>int64 sendTime = 4;</code>
     * @return The sendTime.
     */
    @java.lang.Override
    public long getSendTime() {
      return sendTime_;
    }
    /**
     * <code>int64 sendTime = 4;</code>
     * @param value The sendTime to set.
     * @return This builder for chaining.
     */
    public Builder setSendTime(long value) {
      
      sendTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 sendTime = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearSendTime() {
      
      sendTime_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:ref.parse.SimpleNotifyWithoutMiddleFields)
  }

  // @@protoc_insertion_point(class_scope:ref.parse.SimpleNotifyWithoutMiddleFields)
  private static final com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields();
  }

  public static com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SimpleNotifyWithoutMiddleFields>
      PARSER = new com.google.protobuf.AbstractParser<SimpleNotifyWithoutMiddleFields>() {
    @java.lang.Override
    public SimpleNotifyWithoutMiddleFields parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SimpleNotifyWithoutMiddleFields(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SimpleNotifyWithoutMiddleFields> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SimpleNotifyWithoutMiddleFields> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.examples.protobuf.parse.SimpleNotifyWithoutMiddleFields getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

