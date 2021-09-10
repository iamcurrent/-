package com.example.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreObject {
    Storage storage;
    List<StoreImages> storeImages;
    MonthVisit monthVisit;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<StoreImages> getStoreImages() {
        return storeImages;
    }

    public void setStoreImages(List<StoreImages> storeImages) {
        this.storeImages = storeImages;
    }

    public MonthVisit getMonthVisit() {
        return monthVisit;
    }

    public void setMonthVisit(MonthVisit monthVisit) {
        this.monthVisit = monthVisit;
    }
}
