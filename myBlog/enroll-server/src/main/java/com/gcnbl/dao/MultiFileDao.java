package com.gcnbl.dao;

import com.gcnbl.bean.MultiFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultiFileDao extends JpaRepository<MultiFile,Long> {
    List<MultiFile> findByUserId(Long userId);

    void deleteById(Long id);
}
