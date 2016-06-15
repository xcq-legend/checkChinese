(ns checkChinese.core
  (:use [clojure.java.io])
  (:import java.io.File java.io.InputStream java.io.OutputStream)
  (:gen-class))

(defn hasChineseChar
  "judge the string whether has Chinese char"
  [instr]
  (let [inchars (.getBytes instr)]
    (loop [i 0]
      (if (>= i (alength  inchars)) false
        (if (and (>= (aget inchars i) 0) (<= (aget inchars i) 128))
          (recur (inc i))
          true)))))
(defn checkline
  "check line"
  [linestr]
  (if (hasChineseChar linestr)
    (println "has chinese : " linestr)))

(defn isSourceFile
  "is program file"
  [source]
  (println "source : " source)
  (if (or (.endsWith source ".java")
        (.endsWith source ".js")
        (.endsWith source ".jsp")
        (.endsWith source ".html"))
    true
    false))

(defn checkfile
  "check file"
  [source]
  (if (isSourceFile (.getName source))
    (with-open [sourcereader (reader source)]
      (doall (map checkline (line-seq sourcereader))))
    (println "skip file : " source)))

(defn foo
  "println all the files."
  [source]
  (let [files (file-seq (java.io.File. source))]
    (doseq [sourcefile files]
      (println "==========================================sourcefile : " sourcefile)
      (checkfile sourcefile))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (foo (first args)))