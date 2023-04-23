
package com.koala.tools.models.douyin.v1.roomInfoData;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
public class Text_decoration {

    private int id;
    private Image image;
    private int type;
    private List<Integer> input_rect;
    private int text_size;
    private String text_color;
    private String content;
    private int max_length;
    private int status;
    private int h;
    private int x;
    private int w;
    private int y;
    private int kind;
    private int sub_type;
    private Nine_patch_image nine_patch_image;
    private List<String> text_special_effects;
    private int text_image_adjustable_start_position;
    private int text_image_adjustable_end_position;
    private String audit_text_color;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setImage(Image image) {
         this.image = image;
     }
     public Image getImage() {
         return image;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setInput_rect(List<Integer> input_rect) {
         this.input_rect = input_rect;
     }
     public List<Integer> getInput_rect() {
         return input_rect;
     }

    public void setText_size(int text_size) {
         this.text_size = text_size;
     }
     public int getText_size() {
         return text_size;
     }

    public void setText_color(String text_color) {
         this.text_color = text_color;
     }
     public String getText_color() {
         return text_color;
     }

    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

    public void setMax_length(int max_length) {
         this.max_length = max_length;
     }
     public int getMax_length() {
         return max_length;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setH(int h) {
         this.h = h;
     }
     public int getH() {
         return h;
     }

    public void setX(int x) {
         this.x = x;
     }
     public int getX() {
         return x;
     }

    public void setW(int w) {
         this.w = w;
     }
     public int getW() {
         return w;
     }

    public void setY(int y) {
         this.y = y;
     }
     public int getY() {
         return y;
     }

    public void setKind(int kind) {
         this.kind = kind;
     }
     public int getKind() {
         return kind;
     }

    public void setSub_type(int sub_type) {
         this.sub_type = sub_type;
     }
     public int getSub_type() {
         return sub_type;
     }

    public void setNine_patch_image(Nine_patch_image nine_patch_image) {
         this.nine_patch_image = nine_patch_image;
     }
     public Nine_patch_image getNine_patch_image() {
         return nine_patch_image;
     }

    public void setText_special_effects(List<String> text_special_effects) {
         this.text_special_effects = text_special_effects;
     }
     public List<String> getText_special_effects() {
         return text_special_effects;
     }

    public void setText_image_adjustable_start_position(int text_image_adjustable_start_position) {
         this.text_image_adjustable_start_position = text_image_adjustable_start_position;
     }
     public int getText_image_adjustable_start_position() {
         return text_image_adjustable_start_position;
     }

    public void setText_image_adjustable_end_position(int text_image_adjustable_end_position) {
         this.text_image_adjustable_end_position = text_image_adjustable_end_position;
     }
     public int getText_image_adjustable_end_position() {
         return text_image_adjustable_end_position;
     }

    public void setAudit_text_color(String audit_text_color) {
         this.audit_text_color = audit_text_color;
     }
     public String getAudit_text_color() {
         return audit_text_color;
     }

}