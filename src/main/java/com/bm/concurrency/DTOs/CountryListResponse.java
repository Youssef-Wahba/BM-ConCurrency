package com.bm.concurrency.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryListResponse {
    List<CountriesInfoModel> country_list;
}
