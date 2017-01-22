use monitoramentorpw;

DELETE FROM ocorrencia where id > 0;
DELETE FROM situacao_camera where id > 0;
DELETE FROM mensagem_chat where id > 0;
DELETE FROM chat where id > 0;
DELETE FROM turno where id > 0;
DELETE FROM usuario where usuario NOT LIKE 'brunogft' AND id > 0;
DELETE FROM camera where id > 0;
DELETE FROM campo_ocorrencia where id > 0;
DELETE FROM cliente_ocorrencia where ID_CLIENTE > 0;
DELETE FROM cliente_ocorrencia_personalizada where ID_CLIENTE > 0;
DELETE FROM cliente where id > 0;
DELETE FROM endereco where id > 0;
DELETE FROM telefone where id > 0;