package com.example.Linkdlyst.Features.Urls.Dtos;

import java.util.List;

import com.example.Linkdlyst.Features.Urls.Entities.UrlEntiry;

public record URLListResponse(
    List<UrlEntiry>data,
    long totalCount
){

}
