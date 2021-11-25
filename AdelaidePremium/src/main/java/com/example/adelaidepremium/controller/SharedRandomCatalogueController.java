package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Product;
import com.example.adelaidepremium.interfaces.ProductInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Random;
import java.util.Date;

/***
 * Shared Random Catalogue Controller
 */
public class SharedRandomCatalogueController implements ProductInterface {
  public static ObservableList<Product> PRODUCTLIST_DISCOUNTED;

  /**
   * generate Discounted Catalogue
   *
   * @param PRODUCTLIST
   * @return Discounted Catalogue
   */
  public static ObservableList<Product> generateDiscountedCatalogue(
      ObservableList<Product> PRODUCTLIST) {
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int month = localDate.getMonthValue();
    int year = localDate.getYear();

    //       initializing seed value as year to make sure the discounted catalogue changes every
    // year
    int seed_value = year;

    //        fixing the random catalogue for a period of 3 months
    if (month >= 1 && month <= 3) {
      seed_value += 121;
    } else if (month >= 4 && month <= 6) {
      seed_value += 122;
    } else if (month >= 7 && month <= 9) {
      seed_value += 123;
    } else {
      seed_value += 124;
    }

    long seed = new Random(seed_value).nextLong();

    PRODUCTLIST_DISCOUNTED = FXCollections.observableArrayList(PRODUCTLIST);
    Collections.shuffle(PRODUCTLIST_DISCOUNTED, new Random(seed));

    final int[] count = {0};

    PRODUCTLIST_DISCOUNTED.forEach(
        product -> {
          if (count[0] < 15) {
            product.setPrice(
                (int)
                    (product.getPrice()
                        * ((100 - (new Random(seed).nextInt(5) * 10 + 10)) / 100.0)));
            count[0]++;
          }
        });
    return PRODUCTLIST_DISCOUNTED;
  }
}
