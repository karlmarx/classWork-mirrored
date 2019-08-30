/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarLotServiceLayer;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author karlmarx
 */
public class CarLotServiceImpl implements CarLotService{
    CarLotDAOImpl dao = new CarLotDAOImpl();

    @Override
    public Car getACar(String VIN) {
        return dao.getCar(VIN);
    }

    @Override
    public List<Car> getAllCars() {
        return dao.getCars();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        List<Car> allCars = dao.getCars();
        for (Car eachCar : allCars) {
            if (!eachCar.getColor().equals(color)){
                allCars.remove(eachCar);
            }
        }
        return allCars;
    }

    @Override
    public List<Car> getCarsByPrice(BigDecimal maxPrice) {
         List<Car> allCars = dao.getCars();
        for (Car eachCar : allCars) {
            if (eachCar.getPrice().compareTo(maxPrice) == 1 ){
                allCars.remove(eachCar);
            }
        }
        return allCars;
    }

    @Override
    public BigDecimal discountCar(String VIN, BigDecimal discount) throws NoSuchCarException {
     
        Car discountCar = dao.getCar(VIN);
        if ( discountCar == null) {
            throw new NoSuchCarException("No Such car.");
        }
        discountCar.setPrice(discountCar.getPrice().multiply(BigDecimal.ONE.subtract(discount.divide(new BigDecimal(100)))));
        BigDecimal newPrice = discountCar.getPrice();
        return newPrice; 
    }

    @Override
    public CarKey sellCar(String VIN, BigDecimal cashPaid) throws NoSuchCarException, OverpaidPriceException, UnderpaidPriceException {
            if (cashPaid.compareTo(dao.getCar(VIN).getPrice()) == 0) {
                dao.removeCar(VIN);
                
            } else if (cashPaid.compareTo(dao.getCar(VIN).getPrice()) < 0) {
                throw new UnderpaidPriceException("That's not enough money.");
            } else if (cashPaid.compareTo(dao.getCar(VIN).getPrice()) > 0) {
                throw new OverpaidPriceException("That's too much money.");
            } else if (dao.getCar(VIN) == null) {
                throw new NoSuchCarException("No Such car.");
            }
            return dao.getCar(VIN).getKey();
    }
    
}
