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


    //PROD TYPE
    @RequestMapping(value = "getSort/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sort> getSort(@PathVariable("id") int id)
    {
        Optional<Sort> foundSort = sortDao.findById(id);
        if(foundSort.isPresent())
        {
            Sort s = foundSort.get();
            return ResponseEntity.ok(s);
        }

        else return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "addSort", method = RequestMethod.POST)
    public ResponseEntity<Sort> addSort(@RequestBody Sort sort)
    {
        List<Sort> prodTypes = sortDao.findAll();
        for(Sort s : prodTypes)
        {
            if(s.getSort().equalsIgnoreCase(sort.getSort()))
            {
                    return ResponseEntity.noContent().build();
            }
        }

        sortDao.save(sort);
        return ResponseEntity.ok(sort);
    }

    //SALE

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
                    if(ppq.getProdID() == id)
                    {
                        ppq.setQuantity(ppq.getQuantity() + quantity.getQuantity());
                        stockDao.save(ppq);
                        return ResponseEntity.ok(quantity);
                    }
                }
            }

            Product prod = product.get();
            quantity.setProdID(prod.getProdID());
            stockDao.save(quantity);
            return ResponseEntity.ok(quantity);
        }

        return ResponseEntity.noContent().build();
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





}

