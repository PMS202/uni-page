# Uni-Page: A Universal Pagination Framework for Java 📄

![Uni-Page](https://img.shields.io/badge/Uni--Page-v1.0-blue)

Welcome to the **Uni-Page** repository! This project is a universal pagination framework built on Java. It offers a standardized interface for pagination queries across various data sources. With a modular architecture, Uni-Page currently supports JDBC, Mybatis, MongoDB, Elasticsearch, and more. It also features a flexible extension mechanism, allowing developers to adapt the framework to their specific needs.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Usage](#usage)
- [Supported Data Sources](#supported-data-sources)
- [Extending Uni-Page](#extending-uni-page)
- [Contributing](#contributing)
- [License](#license)
- [Releases](#releases)

## Features

- **Unified Interface**: Provides a consistent pagination interface for different data sources.
- **Modular Architecture**: Easily integrates with various data sources.
- **Flexible Extensions**: Customize and extend the framework as needed.
- **Easy to Use**: Simple API for developers to implement pagination.
- **Open Source**: Free to use and modify.

## Getting Started

To get started with Uni-Page, you can download the latest release from our [Releases section](https://github.com/PMS202/uni-page/releases). Follow the installation instructions to set up the framework in your project.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/PMS202/uni-page.git
   cd uni-page
   ```

2. **Build the Project**:
   Use Maven to build the project.
   ```bash
   mvn clean install
   ```

3. **Add Dependency**:
   Add the following dependency to your `pom.xml`:
   ```xml
   <dependency>
       <groupId>com.example</groupId>
       <artifactId>uni-page</artifactId>
       <version>1.0</version>
   </dependency>
   ```

## Usage

Using Uni-Page is straightforward. Here’s a simple example of how to implement pagination:

```java
import com.example.unipage.Pagination;

public class Example {
    public static void main(String[] args) {
        Pagination pagination = new Pagination();
        pagination.setPage(1);
        pagination.setSize(10);
        
        // Fetch data using your data source
        List<Data> dataList = pagination.fetchData();
        System.out.println(dataList);
    }
}
```

This code initializes pagination, sets the page number and size, and fetches data accordingly.

## Supported Data Sources

Uni-Page currently supports the following data sources:

- **JDBC**: Standard Java Database Connectivity.
- **Mybatis**: A popular persistence framework.
- **MongoDB**: A NoSQL database.
- **Elasticsearch**: A distributed search and analytics engine.

You can easily extend Uni-Page to support additional data sources by implementing the necessary interfaces.

## Extending Uni-Page

To add support for a new data source, follow these steps:

1. **Implement the Pagination Interface**: Create a new class that implements the `PaginationInterface`.
2. **Define Data Fetching Logic**: Implement the method to fetch data from your specific data source.
3. **Register Your Implementation**: Ensure your new implementation is registered with the Uni-Page framework.

This flexibility allows you to adapt Uni-Page to fit your project requirements.

## Contributing

We welcome contributions! If you’d like to contribute to Uni-Page, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Make your changes and commit them (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Releases

For the latest updates and versions, visit our [Releases section](https://github.com/PMS202/uni-page/releases). Here, you can download the latest version and see what's new.

---

Thank you for your interest in Uni-Page! We hope you find this framework useful for your projects. If you have any questions or feedback, feel free to reach out.