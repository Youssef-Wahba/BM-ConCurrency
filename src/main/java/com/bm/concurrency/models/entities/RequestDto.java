package com.bm.concurrency.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto  implements Serializable {

   private Integer baseCurrencyId;
   private  List<Integer> targetCurrencyIds;
   private double amount;

}
