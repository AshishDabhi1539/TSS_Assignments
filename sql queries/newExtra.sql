SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;

-- Ensure auto-increment starts correctly
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE accounts AUTO_INCREMENT = 1;
ALTER TABLE loans AUTO_INCREMENT = 1;
ALTER TABLE transactions AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO users (username, password_hash, full_name, email, phone, role) VALUES
('jay_patel', '$2a$10$ttpvL/b00gyB7KkCegh/DOhZuuViUsQdV76.1.REy0UPhV14Hw8Wi', 'Jay Patel', 'jay.patel@email.com', '9876543210', 'Customer'),
('priya_shah', '$2a$10$1lzs4wFD0y30NMvDfu/OzOq9L3YcIJlKvOggSfbGPFExwSCsptDQy', 'Priya Shah', 'priya.shah@email.com', '9876543211', 'Customer'),
('dhaval_mehta', '$2a$10$AnsHb6a1Z/tIbSOQ.mvhEO98Hlqh15WAKZ064UBUrTlkB7ZW2hma2', 'Dhaval Mehta', 'dhaval.mehta@email.com', '9876543212', 'Customer'),
('kriti_desai', '$2a$10$eIUkdLQyvlUBscqzfSu5LeNWULHvk2Vr5s/ERuhN7mufeI3SpHhXy', 'Kriti Desai', 'kriti.desai@email.com', '9876543213', 'Customer'),
('arjun_bhatt', '$2a$10$7Ksc6YGgsEcv42u2SsjmYOS5Yt10N6rhvS9XmXyFZgZQG/vKCLfYy', 'Arjun Bhatt', 'arjun.bhatt@email.com', '9876543214', 'Customer'),
('neha_joshi', '$2a$10$twLnanqP2Rg.jrn2hTWZJeFkdmmHWpzGr6T1Y5NGLY7BiCr1dkhgG', 'Neha Joshi', 'neha.joshi@email.com', '9876543215', 'Customer'),
('rajan_soni', '$2a$10$eFpPip.JTSqVNN7ba2U4w.uZkR0UvzeUllxil9OtngC0XQIIJioqu', 'Rajan Soni', 'rajan.soni@email.com', '9876543216', 'Customer'),
('smita_ghosh', '$2a$10$ZILh9KSEoc8llnlOTyrUhO2xRfLREZz8.xA1pv9kAd6l.UfRyjlrK', 'Smita Ghosh', 'smita.ghosh@email.com', '9876543217', 'Customer'),
('hardik_rana', '$2a$10$Dcc5OuO4wFOTvRggn5GHw.qhoHtuJVQhxixokpwP5Pd6kRF1jKFXK', 'Hardik Rana', 'hardik.rana@email.com', '9876543218', 'Customer'),
('ananya_parekh', '$2a$10$WsTSMIwLRSuNOEj/j5qhG.Dt3qYzB2qh2YZkvF.G8xSX3FAacgqkq', 'Ananya Parekh', 'ananya.parekh@email.com', '9876543219', 'Customer'),
('vivek_ahuja', '$2a$10$X3I95JMI1fOlYKQg30NZsuPWo9kHhr0ms2RoyHs41nGA7AHfUt.Fy', 'Vivek Ahuja', 'vivek.ahuja@email.com', '9876543220', 'Customer'),
('meera_kapadia', '$2a$10$YDKtdlOtW5UJ757YLCMylupst8fEsAckpKNB1/olZyY4RVZXoCzgO', 'Meera Kapadia', 'meera.kapadia@email.com', '9876543221', 'Customer'),
('chirag_modi', '$2a$10$IPwguq8Iu7LF60qsUAPDLOSXhTH1c.TNa9FHl30t2SexhS2Mjus36', 'Chirag Modi', 'chirag.modi@email.com', '9876543222', 'Customer'),
('divya_ingle', '$2a$10$ZLZzpBywM/7JhoZms9189uFK.N.6OAIiteLVpLPuThCBZszWr324K', 'Divya Ingle', 'divya.ingle@email.com', '9876543223', 'Customer'),
('raj_sharma', '$2a$10$U969Kb0QCYGrC0Bn.ehl0exR.u9b4nwP/P6vN5zMB/On/DB8O33c6', 'Raj Sharma', 'raj.sharma@email.com', '9876543224', 'Customer'),
('sonal_patel', '$2a$10$JDI7OE9dZnNTwUt3OlTo7..ZI7hg6cEimoGTAjVmuetx8LWjbUFFK', 'Sonal Patel', 'sonal.patel@email.com', '9876543225', 'Customer'),
('kunal_dave', '$2a$10$3MjqQd8N/rjiwbdAUYRSgOFz9ht1Myj/o7zOark31ALtLDlpNcimO', 'Kunal Dave', 'kunal.dave@email.com', '9876543226', 'Customer'),
('nisha_purohit', '$2a$10$w4vCt1iFyCF8e4nJP1wMvO7SfDU2aOh.CP.hSpW8IRAE58/8n0UN6', 'Nisha Purohit', 'nisha.purohit@email.com', '9876543227', 'Customer'),
('manan_thakkar', '$2a$10$5ExWGINuWBP1ehBDXSjG9.azV9wHuRguc7RzGpfuaR56P3nqx7/Dq', 'Manan Thakkar', 'manan.thakkar@email.com', '9876543228', 'Customer'),
('riya_choudhary', '$2a$10$szyIMYAWw6/WWljY/Bdl..OhUDAbuwZNyhYpx1qTy2WrB07YL2oJm', 'Riya Choudhary', 'riya.choudhary@email.com', '9876543229', 'Customer'),
('parth_mistry', '$2a$10$m5JtOt7dSiL306FzQqmCNuFcrDVAAJ6VlQlOi8MP/8WlhTDEOZiQm', 'Parth Mistry', 'parth.mistry@email.com', '9876543230', 'Customer'),
('tanvi_raval', '$2a$10$itqVDVNmmug.9m5nnzGO7.dB0ovY6lJc.eAanCEAdjOE8IzlZ4dwa', 'Tanvi Raval', 'tanvi.raval@email.com', '9876543231', 'Customer'),
('yash_shah', '$2a$10$r7t8AbC3R7tnbUYpHuHYUeaDtBuUtDXDXGxl0ALYItzcPyTcfkjgS', 'Yash Shah', 'yash.shah@email.com', '9876543232', 'Customer'),
('krupa_ghelani', '$2a$10$GUqajdX.gP2mGPyBRt8.te.IZ81jQpOI3V8cwvBbxH2MtWqoCdK2C', 'Krupa Ghelani', 'krupa.ghelani@email.com', '9876543233', 'Customer'),
('avinash_rajput', '$2a$10$Aje7N9T9zPM4yL1TOyXrPuMW.sbXbnRg4PHbJYBTqUohx6kuxip4S', 'Avinash Rajput', 'avinash.rajput@email.com', '9876543234', 'Customer'),
('mira_panchal', '$2a$10$lwpk72A4XWPEi85soNXQ4ODN0Tmfg2L9C8AcjI5esDlUhGHXgQpou', 'Mira Panchal', 'mira.panchal@email.com', '9876543235', 'Customer'),
('nishant_kothari', '$2a$10$3..35kbcwClXRqqB87BwE.9N/J.o8UIljS1kCJ2XCzy2cy1d8G.0a', 'Nishant Kothari', 'nishant.kothari@email.com', '9876543236', 'Customer'),
('shreya_nagar', '$2a$10$QYHJSKvIKEz5dn7YpNUCLu/HZTuY9JWU.p49f34Z4umFJkpZsHaym', 'Shreya Nagar', 'shreya.nagar@email.com', '9876543237', 'Customer'),
('harshil_sanghvi', '$2a$10$W5pZSj2h2FfryzFsfSoZPOYnScu2pD04v.U4.9bT5VuKm6HAT5O9G', 'Harshil Sanghvi', 'harshil.sanghvi@email.com', '9876543238', 'Customer'),
('deepa_ved', '$2a$10$cfI6ZzbDRuBZIJkYwTHDgeBpdalK3quMFQ8VqjsTSYQkUBKdHLFWG', 'Deepa Ved', 'deepa.ved@email.com', '9876543239', 'Customer'),
('omkar_doshi', '$2a$10$mfGqGZSCIWtjngDHCQR5Q.6Npz3CO9dlOZYelCMC2kRGbyAejvEue', 'Omkar Doshi', 'omkar.doshi@email.com', '9876543240', 'Customer'),
('trisha_prajapati', '$2a$10$RIy8kB3g07iGD5fMg0Mssesqj8UOa5Ov0QoOMzmE5g6vV4uAcdtgC', 'Trisha Prajapati', 'trisha.prajapati@email.com', '9876543241', 'Customer'),
('devansh_jadeja', '$2a$10$aJ7LEcWvOTnJCs1q0EsH/.GnMXnLU/4jy.6q.xickXCOVW1X9Neau', 'Devansh Jadeja', 'devansh.jadeja@email.com', '9876543242', 'Customer'),
('aanya_patil', '$2a$10$2yTy6S/gd.wINfaDpd8veuk8uppLB2hxWdIeei1h7m38ZFe4wMu3q', 'Aanya Patil', 'aanya.patil@email.com', '9876543243', 'Customer'),
('viral_soni', '$2a$10$zodW5.xTmDlEY8WQ7EXEq.SFhtb4RAXH0wMow3pZqeUOTLN7PCoi.', 'Viral Soni', 'viral.soni@email.com', '9876543244', 'Customer'),
('meenakshi_ghimire', '$2a$10$c7EdF8Mym8oxqerf0tpft.IOSPmhHV9IPW30GtRTaLJFxF73WFA7C', 'Meenakshi Ghimire', 'meenakshi.ghimire@email.com', '9876543245', 'Customer'),
('siddharth_ingle', '$2a$10$4fGAD7HFLb7oD4r3VqOAf.LhuK5vgoQpbckRU4vlc1/A3gBd2/w4G', 'Siddharth Ingle', 'siddharth.ingle@email.com', '9876543246', 'Customer'),
('kavita_pandya', '$2a$10$JpLHFm.w9zVb87AjhORLPO.1SRzBjfe48M9O.XFo2Kv58MGEXlilq', 'Kavita Pandya', 'kavita.pandya@email.com', '9876543247', 'Customer'),
('darshan_rathod', '$2a$10$AeMqnp975xJ0hERK6iCvz.Uium5N35sRzVYQ41ezGBatZvQZbRhdm', 'Darshan Rathod', 'darshan.rathod@email.com', '9876543248', 'Customer'),
('anvi_makwana', '$2a$10$g8xibVLjImGZT1JBhPspbuaH.xSIcZklKeZG1OxxazxT6r.ow/mLC', 'Anvi Makwana', 'anvi.makwana@email.com', '9876543249', 'Customer'),
('chetan_vaghela', '$2a$10$E46TBqwKZm/d8Cdqy0r8eu9lLxoiEx3pzT1jmKrDbQmTmrjGNZfA.', 'Chetan Vaghela', 'chetan.vaghela@email.com', '9876543250', 'Customer'),
('pooja_trivedi', '$2a$10$3D4V3.J5S1te/6NpmVwTX.OxqDDFQTvUId1ydUb/2S7donjEnLG7.', 'Pooja Trivedi', 'pooja.trivedi@email.com', '9876543251', 'Customer'),
('rahul_parmar', '$2a$10$fpqHEZQ5DLOvs9EmmpqWE.bra8rnLqAHU.8Jw6Rt4J.05lzELQn1W', 'Rahul Parmar', 'rahul.parmar@email.com', '9876543252', 'Customer'),
('shivani_dholakia', '$2a$10$s0.ga4dLpgkVjozWY0zbTenGeuyzHeiRwOZHrKZZJWc6W42/yGtRC', 'Shivani Dholakia', 'shivani.dholakia@email.com', '9876543253', 'Customer'),
('mitesh_gandhi', '$2a$10$WHed7stOTISfbrzq9Yn8ruXpZOubMu7J1Z6k3jcQvAhgpNsRWr3te', 'Mitesh Gandhi', 'mitesh.gandhi@email.com', '9876543254', 'Customer'),
('vidhi_brahmbhatt', '$2a$10$u9klsljS6jK99/ShRoj8veIItks.14nJXhNwZsdwE8GQsrJhIruy.', 'Vidhi Brahmbhatt', 'vidhi.brahmbhatt@email.com', '9876543255', 'Customer'),
('sanket_joshi', '$2a$10$oippykaTRZ9KCMWumtNdZuuYfohlUclpC8n36NT3ziAx9e2TPh4BW', 'Sanket Joshi', 'sanket.joshi@email.com', '9876543256', 'Customer'),
('bhavna_suthar', '$2a$10$la9yn4upLzzSG/Bo0Px8MOgih3czUc29VOHDvVT8e7lteAEbKeKN6', 'Bhavna Suthar', 'bhavna.suthar@email.com', '9876543257', 'Customer'),
('jigar_pandit', '$2a$10$JLANPD6YGrEHAu13n15ykuGbEck.1hgCwEwu.dBtrVpqWarptsGtG', 'Jigar Pandit', 'jigar.pandit@email.com', '9876543258', 'Customer'),
('krina_shukla', '$2a$10$a8dq031yjNjyno5bJoxlju/Ow7csCnt8/OUcYJLyqXnH/w.aJGr9m', 'Krina Shukla', 'krina.shukla@email.com', '9876543259', 'Customer');
