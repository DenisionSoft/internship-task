package ru.vk.internship.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.internship.audit.RecordAudit;
import ru.vk.internship.model.Album;
import ru.vk.internship.model.Photo;
import ru.vk.internship.service.AlbumService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/albums")
@AllArgsConstructor
public class AlbumController extends BaseController {

    private final AlbumService albumService;

    @RecordAudit
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

    @RecordAudit
    @GetMapping
    public ResponseEntity<ArrayList<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @RecordAudit
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(albumService.createAlbum(album));
    }

    @RecordAudit
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        return ResponseEntity.ok(albumService.updateAlbum(id, album));
    }

    @RecordAudit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.ok().build();
    }

    @RecordAudit
    @GetMapping("/{id}/photos")
    public ResponseEntity<ArrayList<Photo>> getAlbumPhotos(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumPhotos(id));
    }
}
