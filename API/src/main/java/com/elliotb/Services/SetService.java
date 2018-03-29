package com.elliotb.Services;

import com.elliotb.Entity.Set;

import java.util.List;

public interface SetService {
    int deleteSet(String suid);
    List<Set> populateSets(String id);
}
