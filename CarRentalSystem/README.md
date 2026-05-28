# 🚗 Car Rental System — Java OOP Project

A clean, well-structured **Car Rental System** built in Java that demonstrates all four pillars of Object-Oriented Programming.

---

## 📐 OOP Concepts Demonstrated

| Concept | Where It's Used |
|---|---|
| **Abstraction** | `Vehicle` (abstract class), `RentalService` (interface) |
| **Encapsulation** | All model fields are `private` with getters/setters |
| **Inheritance** | `Car`, `SUV`, `Truck` all extend `Vehicle` |
| **Polymorphism** | `calculateRentalCost()` behaves differently per vehicle type |

---

## 📁 Project Structure

```
CarRentalSystem/
└── src/
    └── com/
        └── carrental/
            ├── Main.java                          # Entry point + demo
            ├── model/
            │   ├── Vehicle.java                   # Abstract base class
            │   ├── Car.java                       # Extends Vehicle
            │   ├── SUV.java                       # Extends Vehicle
            │   ├── Truck.java                     # Extends Vehicle
            │   ├── Customer.java                  # Customer model
            │   └── RentalRecord.java              # Rental transaction
            ├── service/
            │   ├── RentalService.java             # Interface
            │   └── RentalServiceImpl.java         # Implementation
            └── exception/
                ├── VehicleNotAvailableException.java
                └── RecordNotFoundException.java
```

---

## ✨ Features

- Add and manage a fleet of Cars, SUVs, and Trucks
- Register customers
- Rent and return vehicles with availability checks
- Custom pricing per vehicle type (discounts, surcharges)
- Rental receipt generation
- Rental history per customer
- Custom exception handling

---

## 💰 Pricing Logic (Polymorphism in Action)

| Vehicle | Rule |
|---|---|
| **Car** | 10% discount for rentals > 7 days |
| **SUV** | Flat $20 insurance surcharge per rental |
| **Truck** | Extra $15/day if payload > 2 tons |

---

## 🚀 How to Run

### Using Command Line (javac)

```bash
# Navigate into the project folder
cd CarRentalSystem

# Compile all Java files
find src -name "*.java" | xargs javac -d out

# Run the main class
java -cp out com.carrental.Main
```

### Using an IDE (IntelliJ / Eclipse)

1. Open the project folder in your IDE
2. Mark `src` as the **Sources Root**
3. Run `Main.java`

---

## 📋 Sample Output

```
========== ADDING VEHICLES ==========
✔ Vehicle added: [CAR] V001 Toyota Camry (2022) | $55.00/day | Available | 5 seats | Automatic
...

========== RENTING VEHICLES ==========
✔ Rental confirmed! RNT001 | Customer: Arjun Sharma | Vehicle: Toyota Camry | Days: 5 | Cost: $275.00 ...

========== RECEIPT ==========
============================================================
              CAR RENTAL SYSTEM — RECEIPT
============================================================
  Rental ID   : RNT001
  Customer    : Arjun Sharma (ID: C001)
  Vehicle     : Toyota Camry (CAR)
  Total Cost  : $275.00
  Status      : ACTIVE
============================================================
```

---

## 🛠 Tech Stack

- **Language:** Java 11+
- **Concepts:** OOP (Abstraction, Encapsulation, Inheritance, Polymorphism)
- **Libraries:** Java Standard Library only (no external dependencies)

---

## 🤝 Contributing

Pull requests are welcome! Some ideas for extension:
- Add a CLI menu for interactive use
- Persist data to a file or database (JDBC/SQLite)
- Add unit tests with JUnit 5
- Add a GUI with JavaFX

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
