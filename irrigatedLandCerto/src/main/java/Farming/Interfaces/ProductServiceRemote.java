package Farming.Interfaces;

import Farming.Model.Farming;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProductServiceRemote {
    List<Farming> getAllProducts();

    Farming getForId(Long id);
}
