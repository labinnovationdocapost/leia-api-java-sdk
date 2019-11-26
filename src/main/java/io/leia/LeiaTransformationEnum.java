package io.leia;

public enum LeiaTransformationEnum {
    IMAGE("image"),
    TEXT("text"),
    TEXT_TREE("text_tree"),
    AUTOROTATE("autorotate"),
    TRIM("trim"),
    DESKEW("deskew");

    private String text;

    LeiaTransformationEnum(final String text) {
        this.text = text;
    }

    public String getLeiaValue() {
        return text;
    }
}
