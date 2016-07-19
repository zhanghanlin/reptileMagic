package com.demo.java.dao;

import com.demo.java.model.Car;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车源Dao
 */
@Repository
public class CarDao extends AbstractDao<Car> {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public Car get(String id) {
        String sql = "SELECT * FROM CAR WHERE ID = ?";
        List<Car> list = jdbcTemplate.query(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Car.class));
        if (list == null || list.isEmpty())
            return null;
        return list.get(0);
    }

    @Override
    public int save(Car car) {
        String insert_sql = "INSERT INTO CAR(id,car_name,price,on_time," +
                "mileage,speed_case,inspect_expire,safe_expire,accident,user_name,phone,url,address,source)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(insert_sql, new Object[]{car.getId(),
                car.getCarName(),
                car.getPrice(),
                car.getOnTime(),
                car.getMileage(),
                car.getSpeedCase(),
                car.getInspectExpire(),
                car.getSafeExpire(),
                car.getAccident(),
                car.getUserName(),
                car.getPhone(),
                car.getUrl(),
                car.getAddress(),
                car.getSource()
        });
    }

    @Override
    public int update(Car car) {
        String update_sql = "UPDATE CAR SET car_name=?,price=?,on_time=?," +
                "mileage=?,speed_case=?,inspect_expire=?,safe_expire=?,accident=?," +
                "user_name=?,phone=?,url=?,address=?,source=?,update_time=NOW() WHERE id=?";
        return jdbcTemplate.update(update_sql, new Object[]{car.getCarName(),
                car.getPrice(),
                car.getOnTime(),
                car.getMileage(),
                car.getSpeedCase(),
                car.getInspectExpire(),
                car.getSafeExpire(),
                car.getAccident(),
                car.getUserName(),
                car.getPhone(),
                car.getUrl(),
                car.getAddress(),
                car.getSource(),
                car.getId()
        });
    }

    @Override
    public List<Car> list() {
        return jdbcTemplate.query("SELECT * FROM CAR", BeanPropertyRowMapper.newInstance(Car.class));
    }

    /**
     * 分页查询
     *
     * @param start
     * @param size
     * @return
     */
    public List<Car> listByPage(int start, int size) {
        String sql = "SELECT * FROM CAR LIMIT ?,?";
        List<Car> list = jdbcTemplate.query(sql,
                new Object[]{start, size},
                BeanPropertyRowMapper.newInstance(Car.class));
        return list;
    }

    /**
     * 返回总行数量
     *
     * @return
     */
    public int getTotalCount() {
        String sql = "SELECT COUNT(ID) FROM CAR";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
