// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: parse.proto

package com.examples.protobuf.parse;

/**
 * Protobuf type {@code ref.parse.Wallet}
 */
public final class Wallet extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ref.parse.Wallet)
    WalletOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Wallet.newBuilder() to construct.
  private Wallet(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Wallet() {
    baseCoin_ = "";
    quoteCoin_ = "";
    settleCoin_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Wallet();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Wallet(
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

            balance_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            baseCoin_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            quoteCoin_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            settleCoin_ = s;
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
    return com.examples.protobuf.parse.Parse.internal_static_ref_parse_Wallet_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.examples.protobuf.parse.Parse.internal_static_ref_parse_Wallet_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.examples.protobuf.parse.Wallet.class, com.examples.protobuf.parse.Wallet.Builder.class);
  }

  public static final int BALANCE_FIELD_NUMBER = 1;
  private long balance_;
  /**
   * <code>int64 balance = 1;</code>
   * @return The balance.
   */
  @java.lang.Override
  public long getBalance() {
    return balance_;
  }

  public static final int BASECOIN_FIELD_NUMBER = 3;
  private volatile java.lang.Object baseCoin_;
  /**
   * <code>string baseCoin = 3;</code>
   * @return The baseCoin.
   */
  @java.lang.Override
  public java.lang.String getBaseCoin() {
    java.lang.Object ref = baseCoin_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      baseCoin_ = s;
      return s;
    }
  }
  /**
   * <code>string baseCoin = 3;</code>
   * @return The bytes for baseCoin.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getBaseCoinBytes() {
    java.lang.Object ref = baseCoin_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      baseCoin_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int QUOTECOIN_FIELD_NUMBER = 4;
  private volatile java.lang.Object quoteCoin_;
  /**
   * <code>string quoteCoin = 4;</code>
   * @return The quoteCoin.
   */
  @java.lang.Override
  public java.lang.String getQuoteCoin() {
    java.lang.Object ref = quoteCoin_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      quoteCoin_ = s;
      return s;
    }
  }
  /**
   * <code>string quoteCoin = 4;</code>
   * @return The bytes for quoteCoin.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getQuoteCoinBytes() {
    java.lang.Object ref = quoteCoin_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      quoteCoin_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SETTLECOIN_FIELD_NUMBER = 5;
  private volatile java.lang.Object settleCoin_;
  /**
   * <code>string settleCoin = 5;</code>
   * @return The settleCoin.
   */
  @java.lang.Override
  public java.lang.String getSettleCoin() {
    java.lang.Object ref = settleCoin_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      settleCoin_ = s;
      return s;
    }
  }
  /**
   * <code>string settleCoin = 5;</code>
   * @return The bytes for settleCoin.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getSettleCoinBytes() {
    java.lang.Object ref = settleCoin_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      settleCoin_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (balance_ != 0L) {
      output.writeInt64(1, balance_);
    }
    if (!getBaseCoinBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, baseCoin_);
    }
    if (!getQuoteCoinBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, quoteCoin_);
    }
    if (!getSettleCoinBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, settleCoin_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (balance_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, balance_);
    }
    if (!getBaseCoinBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, baseCoin_);
    }
    if (!getQuoteCoinBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, quoteCoin_);
    }
    if (!getSettleCoinBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, settleCoin_);
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
    if (!(obj instanceof com.examples.protobuf.parse.Wallet)) {
      return super.equals(obj);
    }
    com.examples.protobuf.parse.Wallet other = (com.examples.protobuf.parse.Wallet) obj;

    if (getBalance()
        != other.getBalance()) return false;
    if (!getBaseCoin()
        .equals(other.getBaseCoin())) return false;
    if (!getQuoteCoin()
        .equals(other.getQuoteCoin())) return false;
    if (!getSettleCoin()
        .equals(other.getSettleCoin())) return false;
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
    hash = (37 * hash) + BALANCE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getBalance());
    hash = (37 * hash) + BASECOIN_FIELD_NUMBER;
    hash = (53 * hash) + getBaseCoin().hashCode();
    hash = (37 * hash) + QUOTECOIN_FIELD_NUMBER;
    hash = (53 * hash) + getQuoteCoin().hashCode();
    hash = (37 * hash) + SETTLECOIN_FIELD_NUMBER;
    hash = (53 * hash) + getSettleCoin().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.examples.protobuf.parse.Wallet parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.examples.protobuf.parse.Wallet parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.Wallet parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.examples.protobuf.parse.Wallet parseFrom(
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
  public static Builder newBuilder(com.examples.protobuf.parse.Wallet prototype) {
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
   * Protobuf type {@code ref.parse.Wallet}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ref.parse.Wallet)
      com.examples.protobuf.parse.WalletOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_Wallet_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_Wallet_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.examples.protobuf.parse.Wallet.class, com.examples.protobuf.parse.Wallet.Builder.class);
    }

    // Construct using com.examples.protobuf.parse.Wallet.newBuilder()
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
      balance_ = 0L;

      baseCoin_ = "";

      quoteCoin_ = "";

      settleCoin_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.examples.protobuf.parse.Parse.internal_static_ref_parse_Wallet_descriptor;
    }

    @java.lang.Override
    public com.examples.protobuf.parse.Wallet getDefaultInstanceForType() {
      return com.examples.protobuf.parse.Wallet.getDefaultInstance();
    }

    @java.lang.Override
    public com.examples.protobuf.parse.Wallet build() {
      com.examples.protobuf.parse.Wallet result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.examples.protobuf.parse.Wallet buildPartial() {
      com.examples.protobuf.parse.Wallet result = new com.examples.protobuf.parse.Wallet(this);
      result.balance_ = balance_;
      result.baseCoin_ = baseCoin_;
      result.quoteCoin_ = quoteCoin_;
      result.settleCoin_ = settleCoin_;
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
      if (other instanceof com.examples.protobuf.parse.Wallet) {
        return mergeFrom((com.examples.protobuf.parse.Wallet)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.examples.protobuf.parse.Wallet other) {
      if (other == com.examples.protobuf.parse.Wallet.getDefaultInstance()) return this;
      if (other.getBalance() != 0L) {
        setBalance(other.getBalance());
      }
      if (!other.getBaseCoin().isEmpty()) {
        baseCoin_ = other.baseCoin_;
        onChanged();
      }
      if (!other.getQuoteCoin().isEmpty()) {
        quoteCoin_ = other.quoteCoin_;
        onChanged();
      }
      if (!other.getSettleCoin().isEmpty()) {
        settleCoin_ = other.settleCoin_;
        onChanged();
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
      com.examples.protobuf.parse.Wallet parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.examples.protobuf.parse.Wallet) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long balance_ ;
    /**
     * <code>int64 balance = 1;</code>
     * @return The balance.
     */
    @java.lang.Override
    public long getBalance() {
      return balance_;
    }
    /**
     * <code>int64 balance = 1;</code>
     * @param value The balance to set.
     * @return This builder for chaining.
     */
    public Builder setBalance(long value) {
      
      balance_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 balance = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearBalance() {
      
      balance_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object baseCoin_ = "";
    /**
     * <code>string baseCoin = 3;</code>
     * @return The baseCoin.
     */
    public java.lang.String getBaseCoin() {
      java.lang.Object ref = baseCoin_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        baseCoin_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string baseCoin = 3;</code>
     * @return The bytes for baseCoin.
     */
    public com.google.protobuf.ByteString
        getBaseCoinBytes() {
      java.lang.Object ref = baseCoin_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        baseCoin_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string baseCoin = 3;</code>
     * @param value The baseCoin to set.
     * @return This builder for chaining.
     */
    public Builder setBaseCoin(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      baseCoin_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string baseCoin = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearBaseCoin() {
      
      baseCoin_ = getDefaultInstance().getBaseCoin();
      onChanged();
      return this;
    }
    /**
     * <code>string baseCoin = 3;</code>
     * @param value The bytes for baseCoin to set.
     * @return This builder for chaining.
     */
    public Builder setBaseCoinBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      baseCoin_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object quoteCoin_ = "";
    /**
     * <code>string quoteCoin = 4;</code>
     * @return The quoteCoin.
     */
    public java.lang.String getQuoteCoin() {
      java.lang.Object ref = quoteCoin_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        quoteCoin_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string quoteCoin = 4;</code>
     * @return The bytes for quoteCoin.
     */
    public com.google.protobuf.ByteString
        getQuoteCoinBytes() {
      java.lang.Object ref = quoteCoin_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        quoteCoin_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string quoteCoin = 4;</code>
     * @param value The quoteCoin to set.
     * @return This builder for chaining.
     */
    public Builder setQuoteCoin(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      quoteCoin_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string quoteCoin = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearQuoteCoin() {
      
      quoteCoin_ = getDefaultInstance().getQuoteCoin();
      onChanged();
      return this;
    }
    /**
     * <code>string quoteCoin = 4;</code>
     * @param value The bytes for quoteCoin to set.
     * @return This builder for chaining.
     */
    public Builder setQuoteCoinBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      quoteCoin_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object settleCoin_ = "";
    /**
     * <code>string settleCoin = 5;</code>
     * @return The settleCoin.
     */
    public java.lang.String getSettleCoin() {
      java.lang.Object ref = settleCoin_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        settleCoin_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string settleCoin = 5;</code>
     * @return The bytes for settleCoin.
     */
    public com.google.protobuf.ByteString
        getSettleCoinBytes() {
      java.lang.Object ref = settleCoin_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        settleCoin_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string settleCoin = 5;</code>
     * @param value The settleCoin to set.
     * @return This builder for chaining.
     */
    public Builder setSettleCoin(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      settleCoin_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string settleCoin = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearSettleCoin() {
      
      settleCoin_ = getDefaultInstance().getSettleCoin();
      onChanged();
      return this;
    }
    /**
     * <code>string settleCoin = 5;</code>
     * @param value The bytes for settleCoin to set.
     * @return This builder for chaining.
     */
    public Builder setSettleCoinBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      settleCoin_ = value;
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


    // @@protoc_insertion_point(builder_scope:ref.parse.Wallet)
  }

  // @@protoc_insertion_point(class_scope:ref.parse.Wallet)
  private static final com.examples.protobuf.parse.Wallet DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.examples.protobuf.parse.Wallet();
  }

  public static com.examples.protobuf.parse.Wallet getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Wallet>
      PARSER = new com.google.protobuf.AbstractParser<Wallet>() {
    @java.lang.Override
    public Wallet parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Wallet(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Wallet> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Wallet> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.examples.protobuf.parse.Wallet getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

