package org.example.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bank_item")
public class BankItem
{
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private String comments;
    private String itemType;
    private String itemStem;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BankItem(Long itemId, String itemName, String itemDescription, String comments, String itemType,
                    String itemStem, Date createDate, Date modDate, Date deleteDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.comments = comments;
        this.itemType = itemType;
        this.itemStem = itemStem;
        this.createDate = createDate;
        this.modDate = modDate;
        this.deleteDate = deleteDate;

    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemStem() {
        return itemStem;
    }

    public void setItemStem(String itemStem) {
        this.itemStem = itemStem;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }



    @Override
    public String toString() {
        return "BankItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", comments='" + comments + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemStem='" + itemStem + '\'' +
                ", createDate=" + createDate +
                ", modDate=" + modDate +
                ", deleteDate=" + deleteDate +
                '}';
    }

    public BankItem() {
    }


}
