// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: parse.proto

package com.examples.protobuf.parse;

/**
 * Protobuf type {@code ref.parse.CompleteNotifyGateway}
 */
public final class CompleteNotifyGateway extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ref.parse.CompleteNotifyGateway)
    CompleteNotifyGatewayOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CompleteNotifyGateway.newBuilder() to construct.
  private CompleteNotifyGateway(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CompleteNotifyGateway() {
    userId_ = com.google.protobuf.ByteString.EMPTY;
    accountId_ = com.google.protobuf.ByteString.EMPTY;
    version_ = com.google.protobuf.ByteString.EMPTY;
    sendTime_ = com.google.protobuf.ByteString.EMPTY;
    position_ = java.util.Collections.emptyList();
    wallet_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CompleteNotifyGateway();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CompleteNotifyGateway(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 10: {

            userId_ = input.readBytes();
            break;
          }
          case 18: {

            accountId_ = input.readBytes();
            break;
          }
          case 26: {

            version_ = input.readBytes();
            break;
          }
          case 34: {

            sendTime_ = input.readBytes();
            break;
          }
          case 42: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              position_ = new java.util.ArrayList<com.google.protobuf.ByteString>();
              mutable_bitField0_ |= 0x00000001;
            }
            position_.add(input.readBytes());
            break;
          }
          case 50: {

            wallet_ = input.readBytes();
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        position_ = java.util.Collections.unmodifiableList(position_); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.examples.protobuf.parse.Parse.internal_static_ref_parse_CompleteNotifyGateway_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.examples.protobuf.parse.Parse.internal_static_ref_parse_CompleteNotifyGateway_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.examples.protobuf.parse.CompleteNotifyGateway.class, com.examples.protobuf.parse.CompleteNotifyGateway.Builder.class);
  }

  public static final int USERID_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString userId_;
  /**
   * <code>bytes userId = 1;</code>
   * @return The userId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getUserId() {
    return userId_;
  }

  public static final int ACCOUNTID_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString accountId_;
  /**
   * <code>bytes accountId = 2;</code>
   * @return The accountId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getAccountId() {
    return accountId_;
  }

  public static final int VERSION_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString version_;
  /**
   * <code>bytes version = 3;</code>
   * @return The version.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getVersion() {
    return version_;
  }

  public static final int SENDTIME_FIELD_NUMBER = 4;
  private com.google.protobuf.ByteString sendTime_;
  /**
   * <code>bytes sendTime = 4;</code>
   * @return The sendTime.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getSendTime() {
    return sendTime_;
  }

  public static final int POSITION_FIELD_NUMBER = 5;
  private java.util.List<com.google.protobuf.ByteString> position_;
  /**
   * <code>repeated bytes position = 5;</code>
   * @return A list containing the position.
   */
  @java.lang.Override
  public java.util.List<com.google.protobuf.ByteString>
      getPositionList() {
    return position_;
  }
  /**
   * <code>repeated bytes position = 5;</code>
   * @return The count of position.
   */
  public int getPositionCount() {
    return position_.size();
  }
  /**
   * <code>repeated bytes position = 5;</code>
   * @param index The index of the element to return.
   * @return The position at the given index.
   */
  public com.google.protobuf.ByteString getPosition(int index) {
    return position_.get(index);
  }

  public static final int WALLET_FIELD_NUMBER = 6;
  private com.google.protobuf.ByteString wallet_;
  /**
   * <code>bytes wallet = 6;</code>
   * @return The wallet.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getWallet() {
    return wallet_;
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
    if (!userId_.isEmpty()) {
      output.writeBytes(1, userId_);
    }
    if (!accountId_.isEmpty()) {
      output.writeBytes(2, accountId_);
    }
    if (!version_.isEmpty()) {
      output.writeBytes(3, version_);
    }
    if (!sendTime_.isEmpty()) {
      output.writeBytes(4, sendTime_);
    }
    for (int i = 0; i < position_.size(); i++) {
      output.writeBytes(5, position_.get(i));
    }
    if (!wallet_.isEmpty()) {
      output.writeBytes(6, wallet_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!userId_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, userId_);
    }
    if (!accountId_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, accountId_);
    }
    if (!version_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, version_);
    }
    if (!sendTime_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(4, sendTime_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < position_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeBytesSizeNoTag(position_.get(i));
      }
      size += dataSize;
      size += 1 * getPositionList().size();
    }
    if (!wallet_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(6, wallet_);
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
    if (!(obj instanceof com.examples.protobuf.parse.CompleteNotifyGateway)) {
      return super.equals(obj);
    }
    com.examples.protobuf.parse.CompleteNotifyGateway other = (com.examples.protobuf.parse.CompleteNotifyGateway) obj;

    if (!getUserId()
        .equals(other.getUserId())) return false;
    if (!getAccountId()
        .equals(other.getAccountId())) return false;
    if (!getVersion()
        .equals(other.getVersion())) return false;
    if (!getSendTime()
        .equals(other.getSendTime())) return false;
    if (!getPositionList()
        .equals(other.getPositionList())) return false;
    if (!getWallet()
        .equals(other.getWallet())) return false;
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
    hash = (53 * hash) + getUserId().hashCode();
    hash = (37 * hash) + ACCOUNTID_FIELD_NUMBER;
    hash = (53 * hash) + getAccountId().hashCode();
    hash = (37 * hash) + VERSION_FIELD_NUMBER;
    hash = (53 * hash) + getVersion().hashCode();
    hash = (37 * hash) + SENDTIME_FIELD_NUMBER;
    hash = (53 * hash) + getSendTime().hashCode();
    if (getPositionCount() > 0) {
      hash = (37 * hash) + POSITION_FIELD_NUMBER;
      hash = (53 * hash) + getPositionList().hashCode();
    }
    hash = (37 * hash) + WALLET_FIELD_NUMBER;
    hash = (53 * hash) + getWallet().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.CompleteNotifyGateway parseFrom(
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
  public static Builder newBuilder(com.examples.protobuf.parse.CompleteNotifyGateway prototype) {
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
   * Protobuf type {@code ref.parse.CompleteNotifyGateway}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ref.parse.CompleteNotifyGateway)
      com.examples.protobuf.parse.CompleteNotifyGatewayOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_CompleteNotifyGateway_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_CompleteNotifyGateway_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.examples.protobuf.parse.CompleteNotifyGateway.class, com.examples.protobuf.parse.CompleteNotifyGateway.Builder.class);
    }

    // Construct using com.examples.protobuf.parse.CompleteNotifyGateway.newBuilder()
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
      userId_ = com.google.protobuf.ByteString.EMPTY;

      accountId_ = com.google.protobuf.ByteString.EMPTY;

      version_ = com.google.protobuf.ByteString.EMPTY;

      sendTime_ = com.google.protobuf.ByteString.EMPTY;

      position_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      wallet_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_CompleteNotifyGateway_descriptor;
    }

    @java.lang.Override
    public com.examples.protobuf.parse.CompleteNotifyGateway getDefaultInstanceForType() {
      return com.examples.protobuf.parse.CompleteNotifyGateway.getDefaultInstance();
    }

    @java.lang.Override
    public com.examples.protobuf.parse.CompleteNotifyGateway build() {
      com.examples.protobuf.parse.CompleteNotifyGateway result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.examples.protobuf.parse.CompleteNotifyGateway buildPartial() {
      com.examples.protobuf.parse.CompleteNotifyGateway result = new com.examples.protobuf.parse.CompleteNotifyGateway(this);
      int from_bitField0_ = bitField0_;
      result.userId_ = userId_;
      result.accountId_ = accountId_;
      result.version_ = version_;
      result.sendTime_ = sendTime_;
      if (((bitField0_ & 0x00000001) != 0)) {
        position_ = java.util.Collections.unmodifiableList(position_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.position_ = position_;
      result.wallet_ = wallet_;
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
      if (other instanceof com.examples.protobuf.parse.CompleteNotifyGateway) {
        return mergeFrom((com.examples.protobuf.parse.CompleteNotifyGateway)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.examples.protobuf.parse.CompleteNotifyGateway other) {
      if (other == com.examples.protobuf.parse.CompleteNotifyGateway.getDefaultInstance()) return this;
      if (other.getUserId() != com.google.protobuf.ByteString.EMPTY) {
        setUserId(other.getUserId());
      }
      if (other.getAccountId() != com.google.protobuf.ByteString.EMPTY) {
        setAccountId(other.getAccountId());
      }
      if (other.getVersion() != com.google.protobuf.ByteString.EMPTY) {
        setVersion(other.getVersion());
      }
      if (other.getSendTime() != com.google.protobuf.ByteString.EMPTY) {
        setSendTime(other.getSendTime());
      }
      if (!other.position_.isEmpty()) {
        if (position_.isEmpty()) {
          position_ = other.position_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensurePositionIsMutable();
          position_.addAll(other.position_);
        }
        onChanged();
      }
      if (other.getWallet() != com.google.protobuf.ByteString.EMPTY) {
        setWallet(other.getWallet());
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
      com.examples.protobuf.parse.CompleteNotifyGateway parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.examples.protobuf.parse.CompleteNotifyGateway) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.ByteString userId_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes userId = 1;</code>
     * @return The userId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getUserId() {
      return userId_;
    }
    /**
     * <code>bytes userId = 1;</code>
     * @param value The userId to set.
     * @return This builder for chaining.
     */
    public Builder setUserId(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      userId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes userId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserId() {
      
      userId_ = getDefaultInstance().getUserId();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString accountId_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes accountId = 2;</code>
     * @return The accountId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAccountId() {
      return accountId_;
    }
    /**
     * <code>bytes accountId = 2;</code>
     * @param value The accountId to set.
     * @return This builder for chaining.
     */
    public Builder setAccountId(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      accountId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes accountId = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAccountId() {
      
      accountId_ = getDefaultInstance().getAccountId();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString version_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes version = 3;</code>
     * @return The version.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getVersion() {
      return version_;
    }
    /**
     * <code>bytes version = 3;</code>
     * @param value The version to set.
     * @return This builder for chaining.
     */
    public Builder setVersion(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      version_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes version = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearVersion() {
      
      version_ = getDefaultInstance().getVersion();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString sendTime_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes sendTime = 4;</code>
     * @return The sendTime.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSendTime() {
      return sendTime_;
    }
    /**
     * <code>bytes sendTime = 4;</code>
     * @param value The sendTime to set.
     * @return This builder for chaining.
     */
    public Builder setSendTime(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      sendTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes sendTime = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearSendTime() {
      
      sendTime_ = getDefaultInstance().getSendTime();
      onChanged();
      return this;
    }

    private java.util.List<com.google.protobuf.ByteString> position_ = java.util.Collections.emptyList();
    private void ensurePositionIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        position_ = new java.util.ArrayList<com.google.protobuf.ByteString>(position_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated bytes position = 5;</code>
     * @return A list containing the position.
     */
    public java.util.List<com.google.protobuf.ByteString>
        getPositionList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(position_) : position_;
    }
    /**
     * <code>repeated bytes position = 5;</code>
     * @return The count of position.
     */
    public int getPositionCount() {
      return position_.size();
    }
    /**
     * <code>repeated bytes position = 5;</code>
     * @param index The index of the element to return.
     * @return The position at the given index.
     */
    public com.google.protobuf.ByteString getPosition(int index) {
      return position_.get(index);
    }
    /**
     * <code>repeated bytes position = 5;</code>
     * @param index The index to set the value at.
     * @param value The position to set.
     * @return This builder for chaining.
     */
    public Builder setPosition(
        int index, com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensurePositionIsMutable();
      position_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes position = 5;</code>
     * @param value The position to add.
     * @return This builder for chaining.
     */
    public Builder addPosition(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensurePositionIsMutable();
      position_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes position = 5;</code>
     * @param values The position to add.
     * @return This builder for chaining.
     */
    public Builder addAllPosition(
        java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
      ensurePositionIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, position_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes position = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearPosition() {
      position_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString wallet_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes wallet = 6;</code>
     * @return The wallet.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getWallet() {
      return wallet_;
    }
    /**
     * <code>bytes wallet = 6;</code>
     * @param value The wallet to set.
     * @return This builder for chaining.
     */
    public Builder setWallet(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      wallet_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes wallet = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearWallet() {
      
      wallet_ = getDefaultInstance().getWallet();
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


    // @@protoc_insertion_point(builder_scope:ref.parse.CompleteNotifyGateway)
  }

  // @@protoc_insertion_point(class_scope:ref.parse.CompleteNotifyGateway)
  private static final com.examples.protobuf.parse.CompleteNotifyGateway DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.examples.protobuf.parse.CompleteNotifyGateway();
  }

  public static com.examples.protobuf.parse.CompleteNotifyGateway getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CompleteNotifyGateway>
      PARSER = new com.google.protobuf.AbstractParser<CompleteNotifyGateway>() {
    @java.lang.Override
    public CompleteNotifyGateway parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CompleteNotifyGateway(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CompleteNotifyGateway> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CompleteNotifyGateway> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.examples.protobuf.parse.CompleteNotifyGateway getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
