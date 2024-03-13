package ru.vk.internship.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.vk.internship.config.Consts;
import ru.vk.internship.model.Album;
import ru.vk.internship.model.Photo;
import ru.vk.internship.proxy.ProxyClient;
import ru.vk.internship.service.AlbumService;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final ProxyClient proxyClient;

    @Override
    @Cacheable(value = "album", key = "#id")
    public Album getAlbumById(Long id) {
        return proxyClient.get(Consts.URI_ALBUMS + "/" + id, Album.class);
    }

    @Override
    @Cacheable(value = "albums")
    public ArrayList<Album> getAllAlbums() {
        return proxyClient.get(Consts.URI_ALBUMS, ArrayList.class);
    }

    @Override
    public Album createAlbum(Album album) {
        return proxyClient.post(Consts.URI_ALBUMS, album, Album.class);
    }

    @Override
    @CachePut(value = "album", key = "#id")
    public Album updateAlbum(Long id, Album album) {
        return proxyClient.put(Consts.URI_ALBUMS + "/" + id, album, Album.class);
    }

    @Override
    @CacheEvict(value = "album", key = "#id")
    public void deleteAlbum(Long id) {
        proxyClient.delete(Consts.URI_ALBUMS + "/" + id);
    }

    @Override
    @Cacheable(value = "albumPhotos", key = "#id")
    public ArrayList<Photo> getAlbumPhotos(Long id) {
        return proxyClient.get(Consts.URI_ALBUMS + "/" + id + Consts.URI_PHOTOS, ArrayList.class);
    }
}
