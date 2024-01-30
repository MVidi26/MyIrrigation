package Farming.Interfaces;

import Farming.FarmingObj;
import Farming.Model.Farming;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    List<Farming> getAllProducts();

    Farming getForId(Long id);
}
