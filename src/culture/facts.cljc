(ns culture.facts
  "Regional-culture catalog for Barcelona -- local dishes, beverages,
  festivals and heritage sites, piggybacked onto this municipality
  compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"barcelona"
   [{:culture/id "barcelona.dish.pa-amb-tomaquet"
     :culture/name "Pa amb tomàquet"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :dish
     :culture/summary "Bread rubbed with fresh tomato and seasoned with olive oil and salt; considered a staple of Catalonia's cuisine and Catalan identity."
     :culture/url "https://en.wikipedia.org/wiki/Pa_amb_tom%C3%A0quet"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "barcelona.dish.esqueixada"
     :culture/name "Esqueixada"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :dish
     :culture/summary "Traditional Catalan salad of hand-shredded salt cod with tomatoes, onions, olive oil and vinegar."
     :culture/url "https://en.wikipedia.org/wiki/Esqueixada"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "barcelona.dish.botifarra"
     :culture/name "Botifarra"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :dish
     :culture/summary "Type of sausage counted among the most important dishes of Catalan cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Botifarra"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "barcelona.dish.crema-catalana"
     :culture/name "Crema catalana"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :dish
     :culture/summary "Catalan dessert of custard topped with caramelized sugar, flavored with cinnamon and lemon zest."
     :culture/url "https://en.wikipedia.org/wiki/Crema_catalana"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "barcelona.beverage.cava"
     :culture/name "Cava"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :beverage
     :culture/summary "Spanish sparkling wine of denominación de origen status; about 95% is produced in Catalonia's Penedès area near Barcelona."
     :culture/url "https://en.wikipedia.org/wiki/Cava_(Spanish_wine)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "barcelona.festival.la-merce"
     :culture/name "La Mercè"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :festival
     :culture/summary "Annual festival of the city of Barcelona held in late September, featuring castells (human towers), correfoc fire-runs, giant parade figures and sardana dancing."
     :culture/url "https://en.wikipedia.org/wiki/La_Merc%C3%A8"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "barcelona.festival.festa-major-de-gracia"
     :culture/name "Festa Major de Gràcia"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :festival
     :culture/summary "Annual festival of Barcelona's Gràcia district held 15-21 August, renowned for its elaborately decorated streets created by residents."
     :culture/url "https://ca.wikipedia.org/wiki/Festa_Major_de_Gr%C3%A0cia"
     :culture/url-provenance :wikipedia-ca
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "barcelona.heritage.sagrada-familia"
     :culture/name "Sagrada Família"
     :culture/name-local "Basílica de la Sagrada Família"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :heritage
     :culture/summary "Catholic basilica in Barcelona under construction since 1882; a UNESCO World Heritage-listed work combining Gothic and Art Nouveau forms."
     :culture/url "https://en.wikipedia.org/wiki/Sagrada_Fam%C3%ADlia"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "barcelona.heritage.park-guell"
     :culture/name "Park Güell"
     :culture/name-local "Parc Güell"
     :culture/municipality "barcelona"
     :culture/country "ESP"
     :culture/kind :heritage
     :culture/summary "Park complex on Carmel Hill in Barcelona's Gràcia district, built 1900-1914 and opened to the public in 1926; UNESCO World Heritage-listed since 1984."
     :culture/url "https://en.wikipedia.org/wiki/Park_G%C3%BCell"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-esp-barcelona culture catalog "
                 "(ADR-2607171400): " (count (get catalog "barcelona"))
                 " Barcelona entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
