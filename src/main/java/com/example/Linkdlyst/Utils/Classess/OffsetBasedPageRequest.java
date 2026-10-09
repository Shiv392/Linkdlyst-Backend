package com.example.Linkdlyst.Utils.Classess;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.Linkdlyst.Utils.Exceptions.BadRequestException;


public class OffsetBasedPageRequest implements Pageable {
    private int limit;
    private int offset;

    public OffsetBasedPageRequest(int _limit, int _offset){
        if(_offset<0){
            throw new BadRequestException("Invalid offset");
        }
        if(_limit<1){
            throw new BadRequestException("Invalid limit");
        }


        limit = _limit;
        offset = _offset;
    }

        @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(limit, offset + limit);
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious()
                ? new OffsetBasedPageRequest(limit, offset - limit)
                : first();
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(limit, 0);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        if (pageNumber < 0) {
            throw new BadRequestException("Invalid page number");
        }
        return new OffsetBasedPageRequest(limit, Math.multiplyExact(pageNumber, limit));
    }

    @Override
    public boolean hasPrevious() {
        return offset >= limit;
    }
}
