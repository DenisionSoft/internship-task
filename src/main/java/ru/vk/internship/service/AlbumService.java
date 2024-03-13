package ru.vk.internship.service;

import ru.vk.internship.model.Album;
import ru.vk.internship.model.Photo;

import java.util.ArrayList;

public interface AlbumService {
    Album getAlbumById(Long id);
    ArrayList<Album> getAllAlbums();
    Album createAlbum(Album album);
    Album updateAlbum(Long id, Album album);
    void deleteAlbum(Long id);

    ArrayList<Photo> getAlbumPhotos(Long id);
}
