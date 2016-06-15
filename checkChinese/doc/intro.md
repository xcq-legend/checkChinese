# Introduction to copyfolder

TODO: write [great documentation](http://jacobian.org/writing/great-documentation/what-to-write/)
## create
lein new app checkChinese

add :main checkChinese.core to project.clj defproject

(ns checkChinese.core
  (:use [clojure.java.io])
  (:import java.io.File java.io.InputStream java.io.OutputStream)
  (:gen-class))

lein uberjar

