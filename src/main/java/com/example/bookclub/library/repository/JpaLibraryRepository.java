package com.example.bookclub.library.repository;

import com.example.bookclub.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLibraryRepository extends JpaRepository<Library, Long>, LibraryRepository {
}
