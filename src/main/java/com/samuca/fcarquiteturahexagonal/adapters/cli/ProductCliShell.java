package com.samuca.fcarquiteturahexagonal.adapters.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ProductCliShell {

    private final ProductCli productCli;

    public ProductCliShell(ProductCli productCli) {
        this.productCli = productCli;
    }

    @ShellMethod(key = "hello-world")
    public String helloWorld(@ShellOption(defaultValue = "spring") String arg) {
        return "Hello world " + arg;
    }

    @ShellMethod(key = "create")
    public String create(@ShellOption String productId, @ShellOption String productName, @ShellOption double price) {
        return productCli.run("create", productId, productName, price);
    }

    @ShellMethod(key = "enable")
    public String enable(@ShellOption String productId) {
        return productCli.run("enable", productId, null, 0);
    }

    @ShellMethod(key = "disable")
    public String disable(@ShellOption String productId) {
        return productCli.run("disable", productId, null, 0);
    }

    @ShellMethod(key = "get")
    public String get(@ShellOption String productId) {
        return productCli.run("get", productId, null, 0);
    }
}
