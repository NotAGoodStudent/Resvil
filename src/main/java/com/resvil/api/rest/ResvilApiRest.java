package com.resvil.api.rest;
import com.resvil.api.classes.*;
import com.resvil.api.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResvilApiRest
{

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao prodDao;
    @Autowired
    private SortDao sortDao;
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private StockDao stockDao;



    @RequestMapping(value = "getUser/{mail}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("mail") String mail)
    {

        Optional<User> foundUser = userDao.findById(mail);
        if(foundUser.isPresent())
        {
            User u = foundUser.get();
            return ResponseEntity.ok(u);
        }

        else
            {
                return ResponseEntity.noContent().build();
            }


    }

    //USER
    @RequestMapping(value = "addUser", method =  RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        List<User> users = userDao.findAll();
        for(User u : users)
        {
            if(u.getEmail().equals(user.getEmail()))
            {
                return ResponseEntity.noContent().build();

            }
        }

        userDao.save(user);
        return ResponseEntity.ok(user);


    }



    @RequestMapping(value = "addSale/{mail}", method = RequestMethod.POST)
    public ResponseEntity<Sale> addSale(@PathVariable("mail") String mail, @RequestBody Sale sale)
    {
        Optional<User> foundUser = userDao.findById(mail);
        User user = foundUser.get();
        user.getBoughtProducts().add(sale);
        userDao.save(user);
        saleDao.save(sale);
        return ResponseEntity.ok(sale);
    }

    @RequestMapping(value = "getSale/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sale> getSale(@PathVariable("id") int id)
    {
       Optional<Sale> sale = saleDao.findById(id);
       if(sale.isPresent())
       {
           Sale foundSale = sale.get();
           return ResponseEntity.ok(foundSale);
       }

       return ResponseEntity.noContent().build();



    }

    @RequestMapping(value="getProd/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable int id)
    {
        Optional<Product> findprod = prodDao.findById(id);
        if(findprod.isPresent())
        {
            Product product = findprod.get();
            return ResponseEntity.ok(product);
        }
        else return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "addProd/{type}", method = RequestMethod.POST)
    public ResponseEntity<Product> addProd(@PathVariable("type") String type, @RequestBody Product prod)
    {
        List<Sort> types = sortDao.findAll();

        for (Sort s : types)
        {
            if(s.getSort().equalsIgnoreCase(type))
            {
                prod.setProdType(s.getSort());
                prodDao.save(prod);
                updateTypeList(s, prod);
                return ResponseEntity.ok(prod);
            }
        }

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "getType/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sort> getType(@PathVariable int id)
    {
        Optional<Sort> type = sortDao.findById(id);
        if(type.isPresent())
        {
            Sort s = type.get();
            return ResponseEntity.ok(s);
        }
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "addType", method = RequestMethod.POST)
    public ResponseEntity<Sort> addType(@RequestBody Sort type)
    {
        List<Sort> types = sortDao.findAll();

        for(Sort s : types)
        {
            if(s.getSort().equalsIgnoreCase(type.getSort())) return ResponseEntity.noContent().build();
        }
        sortDao.save(type);
        return ResponseEntity.ok(type);
    }

    @RequestMapping(value = "updateTypeList", method = RequestMethod.PUT)
    public ResponseEntity<Sort> updateTypeList(Sort s, Product p)
    {
        s.getProducts().add(p);
        sortDao.save(s);
        return ResponseEntity.ok(s);
    }

    @RequestMapping(value = "addQuantity/{id}", method = RequestMethod.POST)
    public ResponseEntity<Stock> addQuantity(@PathVariable int id, @RequestBody Stock quantity)
    {
        Optional<Product> product = prodDao.findById(id);

        if(product.isPresent())
        {
            List<Stock> getQuantiies = stockDao.findAll();
            if(!getQuantiies.isEmpty())
            {
                for(Stock ppq : getQuantiies)
                {
                    if(ppq.getProd().getProdID() == id)
                    {
                        ppq.setQuantity(ppq.getQuantity() + quantity.getQuantity());
                        stockDao.save(ppq);
                        return ResponseEntity.ok(quantity);
                    }
                }
            }

            Product prod = product.get();
            quantity.setProd(prod);
            stockDao.save(quantity);
            return ResponseEntity.ok(quantity);
        }

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "getQuantity/{id}", method = RequestMethod.GET)
    public ResponseEntity<Stock> getQuantity(@PathVariable int id)
    {
        List<Stock> stocks = stockDao.findAll();
        for(Stock s : stocks)
        {
            if(s.getProd().getProdID() == id) return ResponseEntity.ok(s);
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "deleteUser/{mail}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable String mail)
    {
        Optional<User> delUser = userDao.findById(mail);
        if(delUser.isPresent())
        {
            User user = delUser.get();
            userDao.delete(user);
            return ResponseEntity.ok(user);
        }

        else return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "deleteProduct/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable int id)
    {
        Optional<Product> delProd = prodDao.findById(id);
        if(delProd.isPresent())
        {
            Product prod = delProd.get();
            prodDao.delete(prod);
            return ResponseEntity.ok(prod);
        }

        else return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "deleteType/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Sort> deleteType(@PathVariable int id)
    {
        Optional<Sort> delSort = sortDao.findById(id);
        if(delSort.isPresent())
        {
            Sort sort = delSort.get();
            sortDao.delete(sort);
            return ResponseEntity.ok(sort);
        }

        else return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "updateProd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProd(@PathVariable int id, @RequestBody Product prod)
    {
        Optional<Product> product = prodDao.findById(id);
        if(product.isPresent())
        {
            Product saveData = product.get();
            Product updatedProd = prod;
            updatedProd.setProdID(id);
            updatedProd.setProdType(saveData.getProdType());
            prodDao.save(updatedProd);
            return ResponseEntity.ok(updatedProd);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "updateType/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sort> updateType(@PathVariable int id, @RequestBody Sort sort)
    {
        Optional<Sort> prodType = sortDao.findById(id);
        if(prodType.isPresent())
        {
            Sort saveData = prodType.get();
            Sort s = sort;
            s.setProducts(saveData.getProducts());
            s.setSortID(id);
            sortDao.save(s);
            return ResponseEntity.ok(s);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "updateUser/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable String mail, @RequestBody User user)
    {
        Optional<User> u = userDao.findById(mail);
        if(u.isPresent())
        {
            User saveData = u.get();
            User updatedUser = user;
            updatedUser.setBoughtProducts(saveData.getBoughtProducts());
            userDao.save(updatedUser);
            return ResponseEntity.ok(updatedUser);
        }

        return ResponseEntity.ok().build();
    }







}

