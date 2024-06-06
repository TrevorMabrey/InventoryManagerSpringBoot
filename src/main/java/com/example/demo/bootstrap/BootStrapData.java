package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {



        List<OutsourcedPart> outsourcedParts= (List<OutsourcedPart>) outsourcedPartRepository.findAll();

        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Logitech");
        o.setName("Steering wheel");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("Csl steering wheel"))thePart=part;
        }

        OutsourcedPart p= new OutsourcedPart();
        p.setCompanyName("PlaySeat");
        p.setName("Trophy frame");
        p.setInv(5);
        p.setPrice(20.0);
        p.setId(100L);
        outsourcedPartRepository.save(p);
        thePart=null;
        outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("Trophy frame"))thePart=part;
        }

        OutsourcedPart q= new OutsourcedPart();
        q.setCompanyName("Samsung");
        q.setName("Gaming monitor");
        q.setInv(5);
        q.setPrice(20.0);
        q.setId(100L);
        outsourcedPartRepository.save(q);
       thePart=null;
        outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("Gaming monitor"))thePart=part;
        }

        OutsourcedPart r= new OutsourcedPart();
        r.setCompanyName("Thrustmaster");
        r.setName("TH8 shifter");
        r.setInv(5);
        r.setPrice(20.0);
        r.setId(100L);
        outsourcedPartRepository.save(r);
        thePart=null;
        outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("TH8 shifter"))thePart=part;
        }

        OutsourcedPart s= new OutsourcedPart();
        s.setCompanyName("Fanatec");
        s.setName("CSL elite pedals");
        s.setInv(5);
        s.setPrice(20.0);
        s.setId(100L);
        outsourcedPartRepository.save(s);
        thePart=null;
        outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("CSL elite pedals"))thePart=part;
        }







        System.out.println(thePart.getCompanyName());


        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }


        Product Budget= new Product("Budget setup",100.0,15);
        Product Mid= new Product("Mid-range setup",100.0,15);
        Product racing= new Product("Pro racing setup",100.0,15);
        Product drift= new Product("Pro drift setup",100.0,15);
        Product offroad= new Product("Pro off-road setup",100.0,15);
        productRepository.save(Budget);
        productRepository.save(Mid);
        productRepository.save(racing);
        productRepository.save(drift);
        productRepository.save(offroad);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
