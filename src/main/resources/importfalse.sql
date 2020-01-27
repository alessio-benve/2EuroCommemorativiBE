INSERT INTO utenti (username,password,email,enable) VALUES ('admin','$2y$12$BKix40hq1Yc9WndrE1zBl.WtUz18SosRCgUSmfkBiAFe.KlHqSj8m','admin@admin.admin',1);
INSERT INTO utenti (username,password,email,enable) VALUES ('user','$2y$12$XUGahnIBIl8qDYGeOYH8mu2k0fzb8RfjobS8zPB2ujiG1wJKyy2sG','user@user.user',1);
INSERT INTO utenti (username,password,email,enable) VALUES ('tester','$2y$12$aSRJoSO/r19m6AgzMLqFrO6kd6KUj6NnAfFgCNxDpB0TzXa3HbnG2','tester@tester.tester',1);


INSERT INTO ruoli (nome_ruolo) VALUES ('ROLE_ADMIN');
INSERT INTO ruoli (nome_ruolo) VALUES ('ROLE_USER');
INSERT INTO ruoli (nome_ruolo) VALUES ('ROLE_TESTER');


INSERT INTO utenti_ruoli (utente_id,ruolo_id) VALUES (1,1);
INSERT INTO utenti_ruoli (utente_id,ruolo_id) VALUES (1,2);
INSERT INTO utenti_ruoli (utente_id,ruolo_id) VALUES (1,3);

INSERT INTO utenti_ruoli (utente_id,ruolo_id) VALUES (2,2);

INSERT INTO utenti_ruoli (utente_id,ruolo_id) VALUES (3,3);
INSERT INTO utenti_ruoli (utente_id,ruolo_id) VALUES (3,2);
