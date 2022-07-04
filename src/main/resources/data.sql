INSERT INTO roles(name) VALUES ('WEBSHOP_CUSTOMER');
INSERT INTO roles(name) VALUES ('WEBSHOP_SELLER');
INSERT INTO roles(name) VALUES ('WEBSHOP_ADMIN');

INSERT INTO accounts(uuid, created_at, first_name, last_name, email, password, is_active, phone_number, role_id, hash)
            VALUES ('2fa13b76-1b4e-4963-ae8f-fa9f2718a3c7', '2022-07-04T16:56:46', 'John', 'Doe', 'john.doe@mail.com', 'test-password-not-hashed', false,'+38164789456', 'WEBSHOP_SELLER', '56924196-2f62-49af-9b67-bdd8b6fedc94');

INSERT INTO webshop_seller(uuid, created_at, account_id) VALUES ('2fa13b76-1b4e-4963-ae8f-fa9f2718a3c7', '2022-07-04T16:56:47', 1);

INSERT INTO products(uuid, created_at, name, description, seller_id, is_available, price) VALUES
            ('50a34a76-7e33-44d2-a439-af1ce06c7349', '2022-07-04T16:56:46', 'Samsung S21 Ultra', 'A telephone.', 1, true, 125000.00),
            ('17529e60-600b-4b93-be10-cc19e7312c8b', '2022-07-05T16:56:46', 'Elephant', 'An animal.', 1, true, 12565000.00),
            ('37160e04-7734-420e-b9bf-78a2c1f27d5a', '2022-07-06T16:56:46', 'Toilet paper', '', 1, true, 300.00),
            ('fc3e6228-47d8-4872-9b88-50ce2414ca10', '2022-07-07T16:56:46', 'Water, 2l', '', 1, true, 75.00),
            ('df3bcd16-66eb-4f90-b0be-3fff97046c8a', '2022-07-08T16:56:46', 'Coca-Cola Zero, 2l', '', 1, true, 125.00),
            ('471267b8-8dfe-4618-93f8-7a6cfa5cbfbd', '2022-07-09T16:56:46', 'Tea cups, 6pcs', '', 1, true, 800.00),
            ('1e7dc82c-23d5-4d7e-8218-af8292574ac9', '2022-07-10T16:56:46', 'Spoons, 12pcs', '', 1, true, 600.00),
            ('69605828-1aa4-454b-acd2-8c892962b655', '2022-07-11T16:56:46', 'Notebook, Hard cover', '', 1, true, 225.00),
            ('295b8be2-0b8e-4a83-8c4f-60291aa05393', '2022-07-14T16:56:46', 'MacBook Pro, 2021', 'A laptop.', 1, true, 225000.00),
            ('0206ee93-aefa-46a7-bb7c-9b7df6cd4f0e', '2022-07-24T16:56:46', 'IPhone 13+', 'A phone.', 1, true, 125000.00),
            ('52d4e59d-562b-4955-95c8-68eee543c543', '2022-07-26T16:56:46', 'Wooden Table', '', 1, true, 30000.00),
            ('a7209b98-a287-4eb3-885c-f2efa1668e54', '2022-07-17T16:56:46', 'Chair, Herman Muiler', 'Dolores nonummy diam et duo ad no amet et et sed elitr eirmod dolor consetetur aliquam dolore. Kasd et consectetuer dolores labore est facilisis erat ea ea dolor est nibh in dolor. Clita dolores consequat.', 1, true, 90000.00);