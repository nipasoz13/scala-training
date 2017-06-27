package book.impatient

import scala.beans.BeanProperty

/**
  * Created by npa on 17/06/17.
  */
object Chapter5_Class {

  /** 1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless
    * Methods,” on page 55 so that it doesn’t turn negative at Int.MaxValue . **/
  class Counter(private var value: Int = 0) {
    def increment() {
      if (value == Int.MaxValue) value = 0
      else value += 1
    }

    def current = value
  }

  /** 2. Write a class BankAccount with methods deposit and withdraw , and a read-only
    * property balance . **/
  class BankAccount {
    private var value = 0

    def balance = value

    def withdraw(amount: Int) {
      value -= amount
    }

    def deposit(amount: Int) {
      value += amount
    }
  }

  /** 3. Write a class Time with read-only properties hours and minutes and a method
    * before(other: Time): Boolean that checks whether this time comes before the
    * other. A Time object should be constructed as new Time(hrs, min) , where hrs is in
    * military time format (between 0 and 23). **/
  class Time(val hours: Int, val minutes: Int) {
    if (hours < 0 || hours > 23) {
      throw new IllegalArgumentException("Invalid hour: should be defined between 0 and 23")
    }

    if (minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Invalid minutes: should be defined between 0 and 59")
    }

    def before(time: Time): Boolean = {
      this.minutesAfterMidnight < time.minutesAfterMidnight
    }

    private def minutesAfterMidnight: Int = {
      hours * 60 + minutes - 1
    }
  }

  /** 4. Reimplement the Time class from the preceding exercise so that the internal
    * representation is the number of minutes since midnight (between 0 and 24 ×
    * 60 – 1). Do not change the public interface. That is, client code should be
    * unaffected by your change. **/
  class Time2(val hours: Int, val minutes: Int) {
    private val minutesAfterMidnight = hours * 60 + minutes - 1

    if (hours < 0 || hours > 23) {
      throw new IllegalArgumentException("Invalid hour: should be defined between 0 and 23")
    }

    if (minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Invalid minutes: should be defined between 0 and 59")
    }

    def before(time: Time2): Boolean = {
      this.minutesAfterMidnight < time.minutesAfterMidnight
    }
  }

  /**
    * 5. Make a class Student with read-write JavaBeans properties name (of type String )
    * and id (of type Long ). What methods are generated? (Use javap to check.) Can
    * you call the JavaBeans getters and setters in Scala? Should you?
    */
  class Student(@BeanProperty var name: String, @BeanProperty var id: Long) {
  }

  /**
    * Javap result
    * Compiled from "Student.scala"
    * public class Student {
    * public java.lang.String name();
    * public void name_$eq(java.lang.String);
    * public long id();
    * public void id_$eq(long);
    * public java.lang.String getName();
    * public void setName(java.lang.String);
    * public long getId();
    * public void setId(long);
    * public Student(java.lang.String, long);
    * }
    * Getter et setter are accessible from Scala code
    */


}
