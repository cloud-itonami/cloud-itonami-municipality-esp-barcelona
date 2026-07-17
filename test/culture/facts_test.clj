(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest barcelona-has-culture-basis
  (let [sb (facts/spec-basis "barcelona")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "barcelona" (:culture/municipality %)) sb))
    (is (every? #(= "ESP" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "girona")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["barcelona" "girona"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["girona"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 4 (count (facts/by-kind "barcelona" :dish))))
  (is (= ["barcelona.beverage.cava"]
         (mapv :culture/id (facts/by-kind "barcelona" :beverage))))
  (is (= 2 (count (facts/by-kind "barcelona" :festival))))
  (is (empty? (facts/by-kind "barcelona" :craft)))
  (is (empty? (facts/by-kind "girona" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
