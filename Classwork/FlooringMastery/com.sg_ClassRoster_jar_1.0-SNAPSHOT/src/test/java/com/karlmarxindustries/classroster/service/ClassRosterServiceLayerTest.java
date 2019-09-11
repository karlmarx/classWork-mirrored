///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.karlmarxindustries.classroster.service;
//
//import com.karlmarxindustries.flooring.service.FlooringServiceLayerImpl;
//import com.karlmarxindustries.flooring.service.FlooringDataValidationException;
//import com.karlmarxindustries.flooring.service.FlooringDuplicateIdException;
//import com.karlmarxindustries.flooring.dao.FlooringAuditDaoStubImpl;
//import com.karlmarxindustries.flooring.dao.FlooringDaoStubImpl;
//import com.karlmarxindustries.flooring.dto.Order;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import com.karlmarxindustries.flooring.service.FlooringServiceLayer;
//import com.karlmarxindustries.flooring.dao.FlooringDao;
//import com.karlmarxindustries.flooring.dao.FlooringAuditDao;
//
///**
// *
// * @author karlmarx
// */
//public class ClassRosterServiceLayerTest {
//    private FlooringServiceLayer service;
//    public ClassRosterServiceLayerTest() {
//        FlooringDao dao = new FlooringDaoStubImpl();
//    FlooringAuditDao auditDao = new FlooringAuditDaoStubImpl();
//   
//    service = new FlooringServiceLayerImpl(dao, auditDao);
//    }
//
//  @Test
//public void testCreateStudent() throws Exception {
//    Order student = new Order("0002");
//    student.setFirstName("Sally");
//    student.setLastName("Smith");
//    student.setCohort("Java-Jan-2015");
//    service.calculateAndOrderNumber(student);
//}
//
//@Test
//public void testCreateStudentDuplicateId() throws Exception {
//    Order student = new Order("0001");
//    student.setFirstName("Sally");
//    student.setLastName("Smith");
//    student.setCohort("Java-Jan-2015");
//   
//    try {
//        service.calculateAndOrderNumber(student);
//        Assertions.fail("Expected ClassRosterDuplicateIdException was not thrown.");
//    } catch (FlooringDuplicateIdException e) {
//        return;
//    }
//   
//}
//@Test
//public void testCreateStudentInvalidData() throws Exception {
//    Order student = new Order("0002");
//    student.setFirstName("");
//    student.setLastName("Smith");
//    student.setCohort("Java-Jan-2015");
//   
//    try {
//        service.calculateAndOrderNumber(student);
//        Assertions.fail("Expected ClassRosterDataValidationException was not thrown.");
//    } catch (FlooringDataValidationException e) {
//        return;
//    }
//   
//}
//@Test
//public void testGetAllStudents() throws Exception {
//    Assertions.assertEquals(1, service.getAllStudents().size());
//}
//@Test
//public void testGetStudent() throws Exception {
//    Order student = service.getStudent("0001");
//    Assertions.assertNotNull(student);
//    student = service.getStudent("9999");
//    Assertions.assertNull(student);
//}
//@Test
//public void testRemoveStudent() throws Exception {
//    Order student = service.removeStudent("0001");
//    Assertions.assertNotNull(student);
//    student = service.removeStudent("9999");
//    Assertions.assertNull(student);
//}
//    
//}
