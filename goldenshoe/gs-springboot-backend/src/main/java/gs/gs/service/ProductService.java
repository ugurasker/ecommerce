package gs.gs.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ProductService {
    public Set<String> findColors(long ProductId);
    public Set<Integer> findSizeByColor(long ProductId, String color);
    public int calculateQuantityByColorAndSize(long ProductId, String color, int size);
}
