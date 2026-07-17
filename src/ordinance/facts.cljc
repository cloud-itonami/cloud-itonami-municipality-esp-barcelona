(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Barcelona (Ajuntament de
  Barcelona) -- a Wave 1b addition per ADR-2607171400 addendum 2, joining
  the cloud-itonami-municipality-* compliance-fact family of
  ADR-2607141700 (cloud-itonami-compliance-fact-federation; see
  cloud-itonami-municipality-esp-madrid for the Spanish sibling).

  Every entry cites an OFFICIAL Ajuntament de Barcelona URL (the Gaseta
  Municipal on w123.bcn.cat, the city's official gazette) -- never
  fabricated. An ordinance not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url/number.

  Both entries below were verified on 2026-07-17 by downloading each
  Gaseta Municipal PDF from w123.bcn.cat and reading its text directly
  via the Read tool: the convivència entry is the Gaseta publication of
  15-01-2026 (ANUNCI Exp. núm. 471/2023 DSAJ) whose text states the
  ordinance's original approval date (23 de desembre de 2005) and the
  Plenari del Consell Municipal's definitive approval of its modification
  on 19 de desembre de 2025; the terrasses-manual entry is the Gaseta
  publication of 20-07-2022 reproducing the Decret d'Alcaldia of
  15-07-2022. Note: ajuntament.barcelona.cat itself refused automated
  fetches (HTTP 418) on 2026-07-17, so the gazette host w123.bcn.cat --
  equally official, footer 'barcelona.cat/gasetamunicipal' -- is cited
  instead. The gazette PDFs incidentally print office-holders' names in
  signature/attendance lines; those were read only to locate titles and
  dates and are NOT stored anywhere in this catalog, consistent with this
  family's no-personal-names discipline.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"barcelona"
   [{:ordinance/id "barcelona.ordenanca-convivencia-espai-public-2005"
     :ordinance/title "Ordenança de mesures per fomentar i garantir la convivència ciutadana a l'espai públic de Barcelona"
     :ordinance/municipality "barcelona"
     :ordinance/country "ESP"
     :ordinance/kind :ordinance
     :ordinance/number "Aprovada el 23 de desembre de 2005; ordenança de modificació aprovada definitivament pel Plenari del Consell Municipal el 19 de desembre de 2025 (Exp. núm. 471/2023 DSAJ)"
     :ordinance/url "https://w123.bcn.cat/APPS/egaseta/home.do?reqCode=downloadFile&publicacionsId=33425"
     :ordinance/url-provenance :official-bcn-cat-gaseta-municipal
     :ordinance/enacted-date "2005-12-23"
     :ordinance/last-revised-date "2025-12-19"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:public-space :civic-behavior}}
    {:ordinance/id "barcelona.decret-manual-operatiu-terrasses-2022"
     :ordinance/title "Decret d'Alcaldia pel qual s'aprova definitivament la modificació del Manual Operatiu de Terrasses"
     :ordinance/municipality "barcelona"
     :ordinance/country "ESP"
     :ordinance/kind :decree
     :ordinance/number "Decret d'Alcaldia de 15 de juliol de 2022 (Manual Operatiu de Terrasses aprovat per Decret d'Alcaldia de 26 de febrer de 2015; Gaseta Municipal de 20 de juliol de 2022)"
     :ordinance/url "https://w123.bcn.cat/APPS/egaseta/home.do?reqCode=downloadFile&publicacionsId=23543"
     :ordinance/url-provenance :official-bcn-cat-gaseta-municipal
     :ordinance/enacted-date "2022-07-15"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:public-space :commerce}}]})

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
      :note (str "cloud-itonami-municipality-esp-barcelona Wave 1b (ADR-2607171400 "
                 "addendum 2 / family ADR-2607141700): "
                 (count (get catalog "barcelona")) " Barcelona entries seeded with "
                 "official Gaseta Municipal (w123.bcn.cat) citations. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
