(ns ordinance.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest barcelona-has-spec-basis
  (let [sb (facts/spec-basis "barcelona")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://w123.bcn.cat/APPS/egaseta/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "girona")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["barcelona" "girona"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["girona"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["barcelona.ordenanca-convivencia-espai-public-2005"]
         (mapv :ordinance/id (facts/by-topic "barcelona" :civic-behavior))))
  (is (empty? (facts/by-topic "barcelona" :labor)))
  (is (empty? (facts/by-topic "girona" :transparency))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/datascript-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
