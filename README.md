# cloud-itonami-municipality-esp-barcelona

Municipal-ordinance compliance catalog for **Barcelona** (Ajuntament de
Barcelona) — a Wave 1b addition per ADR-2607171400 addendum 2, joining
the `cloud-itonami-municipality-*` compliance-fact family of
ADR-2607141700 (`cloud-itonami-compliance-fact-federation`, in
`com-junkawasaki/root`; see e.g.
[`cloud-itonami-municipality-esp-madrid`](https://github.com/cloud-itonami/cloud-itonami-municipality-esp-madrid)
and
[`cloud-itonami-municipality-ita-roma`](https://github.com/cloud-itonami/cloud-itonami-municipality-ita-roma)).
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family.

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on the Ajuntament de
Barcelona's behalf.

Coverage is reported honestly (see `ordinance.facts/coverage`): a
municipality not in `catalog` has **no spec-basis**, full stop — never
fabricate one.

## Data

- `src/ordinance/facts.cljc` — the catalog, source of truth.
- `schema/ordinance.edn` — DataScript schema.
- `data/datascript-tx.edn` — derived DataScript tx-data (query this
  alongside other `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`).

Both entries were verified on 2026-07-17 by downloading Gaseta Municipal
PDFs from the city's official gazette host (`w123.bcn.cat`, footer
`barcelona.cat/gasetamunicipal`) and directly reading the PDF text: the
**Ordenança de mesures per fomentar i garantir la convivència ciutadana
a l'espai públic de Barcelona** (approved 23-12-2005; its modification
ordinance approved definitively by the Plenari del Consell Municipal on
19-12-2025, Exp. núm. 471/2023 DSAJ, Gaseta of 15-01-2026) and the
**Decret d'Alcaldia of 15-07-2022** definitively approving the
modification of the Manual Operatiu de Terrasses (Gaseta of 20-07-2022).
`ajuntament.barcelona.cat` itself refused automated fetches (HTTP 418)
on the verification date, so the equally official gazette host is cited.

## Culture catalog

Alongside the ordinance catalog, this repo carries a **regional-culture
catalog** (ADR-2607171400, `cloud-itonami-municipality-culture-catalog`
in `com-junkawasaki/root`) — local dishes, beverages, festivals and
heritage sites for Barcelona:

- `src/culture/facts.cljc` — the catalog, source of truth.
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

Same provenance discipline as the ordinance catalog: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention). Ordinance text
itself remains the Ajuntament de Barcelona's; this repo stores only
citation metadata (id/title/url/dates), not full text.
