package com.gcnbl.service;

import com.gcnbl.bean.MultiFile;

import java.util.List;

public interface MultiFileService {
    List<MultiFile> findAllFiles();


    MultiFile addFile(MultiFile multiFile);

    MultiFile findById(String id);

    void update(MultiFile multiFile);

    List<MultiFile> findByUserId(Long id);

    void delete(String id);
}
