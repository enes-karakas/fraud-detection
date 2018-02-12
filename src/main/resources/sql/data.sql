--CLIENT
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('john.winterberg@gmail.com', '$2a$04$hXCutIeR7fPkqDllYx5crOpbHfjgh3bLK4cGm3RvFPwtYVYHUNFAW'); --zoetzure_saus
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('sven.svenson@live.se', '$2a$04$4CAIHjfVLb/eP5V0yRLAC.qn4RWfvq4rrLpfX04MhSP8rnxzBJAbG'); --calorierijk-water
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('karel.dedecker@hotmail.com', '$2a$04$7KCm298oSUFIFpMZpxRfRu4phvQQdUT2Y58eDShmdMULAiDR3N8YK'); --herstelde_schedelbreuk
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('start.met.voorsprong@kdg.be', '$2a$04$SsWuimtQqOFIS8ZY8OrTXuqI3.ZduT.rXEd8NSLVNLEhZ53p.Bd4G'); --onvegetarische_groenten

--STAFF
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES('jos.desos@celestial.insurances.be', '$2a$04$R7beNUzipFEhkYqKY3n7zepWwsWcHZc6f8Siz0bnzRv2A7cnTwnle'); --barbecuehandelaar_met_strafblad
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('josef.janssens@celestial.insurances.be', '$2a$04$HEUusPHOx6fSgQUE6k6mHuJjeASiikDTRcaZylcHkUg30RcAIGyDq'); --trotse_ketchup_dief
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('bart.pauwels@celestial.insurances.be', '$2a$04$COcBiUay6k6dlmaGZJurJ.8S8iQR..tTxbF9OHF3HReOxgu/7697a'); --ik_verzamel_dode_vogels
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('chaim.weinstein@celestial.insurances.be', '$2a$04$TetK3MXypHa34BKq1NMBi.nA/iGkYbR78AqIq2wwk9oxN1ZMRc5Xq'); --vissen_zijn_voedsel


INSERT INTO ROLE (ROLE_TYPE, "USER_USER_ID") VALUES ('ROLE_CLIENT', 1);
INSERT INTO ROLE (ROLE_TYPE, "USER_USER_ID") VALUES ('ROLE_CLIENT', 2);
INSERT INTO ROLE (ROLE_TYPE, "USER_USER_ID") VALUES ('ROLE_CLIENT', 3);
INSERT INTO ROLE (ROLE_TYPE, "USER_USER_ID") VALUES ('ROLE_CLIENT', 4);
INSERT INTO ROLE (ROLE_TYPE, "USER_USER_ID") VALUES ('ROLE_STAFF', 5);
INSERT INTO ROLE (ROLE_TYPE, "USER_USER_ID") VALUES ('ROLE_STAFF', 6);
INSERT INTO ROLE (ROLE_TYPE, "USER_USER_ID") VALUES ('ROLE_STAFF', 7);
INSERT INTO ROLE (ROLE_TYPE, "USER_USER_ID") VALUES ('ROLE_STAFF', 8);
