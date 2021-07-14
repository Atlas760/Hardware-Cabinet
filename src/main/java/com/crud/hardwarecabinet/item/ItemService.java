package com.crud.hardwarecabinet.item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@EnableMapRepositories
public class ItemService {
    private final CrudRepository<Item, Long> repository;

    //In-memory store
    public ItemService(CrudRepository<Item, Long> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultItems());
    }

    //List of always available items
    private static List<Item> defaultItems() {
        return List.of(
                new Item(1626255836256L, "Bronze Tier PC", 59999L, "CPU Intel Core i5 8400 | GPU Nvidia GeForce GTX 1650 Super",
                        "https://www.pngfind.com/pngs/m/184-1842836_intel-h310-budget-gaming-desktop-hd-png-download.png"),

                new Item(1626255903636L, "Silver Tier PC", 89999L, "CPU Intel Core i7 9700 | GPU Nvidia GeForce GTX 1660 Super ",
                        "https://www.hp.com/wcsstore/hpusstore/Treatment/mdps/pavilion-gaming-dt/pavilion_gaming_pc_q3fy19_ksp7_mob2.png"),

                new Item(1626255911989L, "Gold Tier PC", 139999L, "CPU AMD Ryzen 5 3600 | GPU Nvidia GeForce RTX 2070",
                        "http://media.flixcar.com/f360cdn/Lenovo-51792940-lenovo-tower-legion-t730-hero.png"),

                new Item(1626255921088L, "Platinum Tier PC", 179999L,
                        "CPU AMD Ryzen 7 3700X | GPU Nvidia GeForce RTX 3070",
                        "https://vrlatech.com/wp-content/uploads/2019/08/Titan.png")
        );
    }

    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Item> find(Long id) {
        return repository.findById(id);
    }

    public Item create(Item item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Item copy = new Item(
                new Date().getTime(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getImage()
        );
        return repository.save(copy);
    }

    public Optional<Item> update( Long id, Item newItem) {
        // Only update an item if it can be found first.
        return repository.findById(id)
                .map(oldItem -> {
                    Item updated = oldItem.updateWith(newItem);
                    return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
