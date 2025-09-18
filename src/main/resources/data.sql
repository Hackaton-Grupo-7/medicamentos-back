-- Insert users
INSERT INTO users (username, name, email, password, role) VALUES
('admin','name', 'admin@happytravel.com', '$2a$10$HsMF2wIVlZAelTWGNHD/r.lbHJemKWx0.HEfqHKHF91CR8R3fDjX2', 'ADMIN'),
('user','name',  'user@happytravel.com', '$2a$10$HsMF2wIVlZAelTWGNHD/r.lbHJemKWx0.HEfqHKHF91CR8R3fDjX2', 'USER'),
('john_doe','name',  'john@example.com', '$2a$10$HsMF2wIVlZAelTWGNHD/r.lbHJemKWx0.HEfqHKHF91CR8R3fDjX2', 'USER'),
('jane_smith','name',  'jane@example.com', '$2a$10$HsMF2wIVlZAelTWGNHD/r.lbHJemKWx0.HEfqHKHF91CR8R3fDjX2', 'USER'),
('mike_wilson','name',  'mike@example.com', '$2a$10$HsMF2wIVlZAelTWGNHD/r.lbHJemKWx0.HEfqHKHF91CR8R3fDjX2', 'USER'),
('sarah_jones','name',  'sarah@example.com', '$2a$10$HsMF2wIVlZAelTWGNHD/r.lbHJemKWx0.HEfqHKHF91CR8R3fDjX2', 'USER'),
('david_brown','name',  'david@example.com', '$2a$10$HsMF2wIVlZAelTWGNHD/r.lbHJemKWx0.HEfqHKHF91CR8R3fDjX2', 'USER');

---- Insert user roles
--INSERT INTO user_role (user_id, role) VALUES
--(1, 'ADMIN'),
--(2, 'USER'),
--(3, 'USER'),
--(4, 'USER'),
--(5, 'USER'),
--(6, 'USER'),
--(7, 'USER');

-- =========================================
-- Insert medications for user 1 (admin)
-- =========================================

-- Anti-inflammatories (20 medications)
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Ibuprofen', 400, '08:00', FALSE, TRUE, 'Anti-inflammatory and pain reliever', NOW(), 1),
('Naproxen', 500, '09:00', FALSE, TRUE, 'Anti-inflammatory and pain reliever', NOW(), 1),
('Diclofenac', 50, '07:30', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 1),
('Ketoprofen', 100, '08:30', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 1),
('Celecoxib', 200, '10:00', FALSE, TRUE, 'COX-2 selective inhibitor', NOW(), 1),
('Meloxicam', 15, '09:15', FALSE, TRUE, 'Anti-inflammatory for arthritis', NOW(), 1),
('Aspirin', 325, '08:00', FALSE, TRUE, 'Pain reliever and blood thinner', NOW(), 1),
('Indomethacin', 25, '08:45', FALSE, TRUE, 'Strong anti-inflammatory', NOW(), 1),
('Piroxicam', 20, '07:00', FALSE, TRUE, 'Long-acting anti-inflammatory', NOW(), 1),
('Sulindac', 150, '08:15', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 1),
('Tolmetin', 400, '09:00', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 1),
('Etodolac', 300, '10:30', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 1),
('Fenoprofen', 300, '08:00', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 1),
('Flurbiprofen', 100, '09:00', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 1),
('Meclofenamate', 100, '07:15', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 1),
('Oxaprozin', 600, '08:00', FALSE, TRUE, 'Long-acting anti-inflammatory', NOW(), 1),
('Ketorolac', 10, '06:45', FALSE, TRUE, 'Strong pain reliever', NOW(), 1),
('Nimesulide', 100, '09:30', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 1),
('Aceclofenac', 100, '08:00', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 1),
('Lornoxicam', 8, '09:00', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 1);

-- Analgesics (20 medications)
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Paracetamol', 500, '08:00', FALSE, TRUE, 'Pain reliever and fever reducer', NOW(), 1),
('Tramadol', 50, '07:30', FALSE, TRUE, 'Moderate to strong pain reliever', NOW(), 1),
('Codeine', 30, '08:30', FALSE, TRUE, 'Opioid pain medication', NOW(), 1),
('Oxycodone', 10, '10:00', FALSE, TRUE, 'Strong opioid pain medication', NOW(), 1),
('Fentanyl', 25, '09:15', FALSE, TRUE, 'Powerful opioid pain medication', NOW(), 1),
('Morphine', 15, '08:45', FALSE, TRUE, 'Strong opioid pain reliever', NOW(), 1),
('Hydrocodone', 10, '07:00', FALSE, TRUE, 'Opioid pain medication', NOW(), 1),
('Methadone', 10, '06:30', FALSE, TRUE, 'Long-acting opioid', NOW(), 1),
('Pethidine', 50, '08:00', FALSE, TRUE, 'Opioid pain medication', NOW(), 1),
('Buprenorphine', 5, '09:30', FALSE, TRUE, 'Partial opioid agonist', NOW(), 1),
('Pentazocine', 50, '07:15', FALSE, TRUE, 'Mixed opioid agonist', NOW(), 1),
('Nalbuphine', 10, '06:45', FALSE, TRUE, 'Mixed opioid agonist', NOW(), 1),
('Butorphanol', 2, '08:15', FALSE, TRUE, 'Mixed opioid agonist', NOW(), 1),
('Dextropropoxyphene', 100, '09:00', FALSE, TRUE, 'Mild opioid pain reliever', NOW(), 1),
('Hydromorphone', 2, '10:30', FALSE, TRUE, 'Strong opioid pain medication', NOW(), 1),
('Oxymorphone', 5, '07:00', FALSE, TRUE, 'Strong opioid pain medication', NOW(), 1),
('Tapentadol', 50, '08:00', FALSE, TRUE, 'Centrally acting analgesic', NOW(), 1),
('Pregabalin', 150, '09:00', FALSE, TRUE, 'Neuropathic pain medication', NOW(), 1),
('Gabapentin', 300, '10:00', FALSE, TRUE, 'Neuropathic pain medication', NOW(), 1),
('Duloxetine', 60, '08:00', FALSE, TRUE, 'Antidepressant for chronic pain', NOW(), 1);

-- Antibiotics (20 medications)
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Amoxicillin', 500, '08:00', FALSE, TRUE, 'Broad-spectrum antibiotic', NOW(), 1),
('Cephalexin', 500, '09:00', FALSE, TRUE, 'First-generation cephalosporin', NOW(), 1),
('Azithromycin', 250, '07:30', FALSE, TRUE, 'Macrolide antibiotic', NOW(), 1),
('Clarithromycin', 500, '08:30', FALSE, TRUE, 'Macrolide antibiotic', NOW(), 1),
('Doxycycline', 100, '10:00', FALSE, TRUE, 'Tetracycline antibiotic', NOW(), 1),
('Ciprofloxacin', 500, '09:15', FALSE, TRUE, 'Fluoroquinolone antibiotic', NOW(), 1),
('Levofloxacin', 500, '08:45', FALSE, TRUE, 'Fluoroquinolone antibiotic', NOW(), 1),
('Moxifloxacin', 400, '07:00', FALSE, TRUE, 'Fourth-generation fluoroquinolone', NOW(), 1),
('Penicillin', 500, '06:30', FALSE, TRUE, 'Beta-lactam antibiotic', NOW(), 1),
('Ampicillin', 500, '08:00', FALSE, TRUE, 'Broad-spectrum penicillin', NOW(), 1),
('Clindamycin', 300, '09:30', FALSE, TRUE, 'Lincosamide antibiotic', NOW(), 1),
('Erythromycin', 500, '07:15', FALSE, TRUE, 'Macrolide antibiotic', NOW(), 1),
('Tetracycline', 500, '06:45', FALSE, TRUE, 'Broad-spectrum antibiotic', NOW(), 1),
('Minocycline', 100, '08:15', FALSE, TRUE, 'Second-generation tetracycline', NOW(), 1),
('Ofloxacin', 400, '09:00', FALSE, TRUE, 'Second-generation fluoroquinolone', NOW(), 1),
('Norfloxacin', 400, '10:30', FALSE, TRUE, 'Second-generation fluoroquinolone', NOW(), 1),
('Trimethoprim', 160, '07:00', FALSE, TRUE, 'Antifolate antibiotic', NOW(), 1),
('Sulfamethoxazole', 800, '08:00', FALSE, TRUE, 'Sulfonamide antibiotic', NOW(), 1),
('Metronidazole', 500, '09:00', FALSE, TRUE, 'Antiprotozoal and antibacterial', NOW(), 1),
('Vancomycin', 1000, '10:00', FALSE, TRUE, 'Glycopeptide antibiotic', NOW(), 1);

-- Antivirals (20 medications)
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Acyclovir', 400, '08:00', FALSE, TRUE, 'Antiviral for herpes infections', NOW(), 1),
('Valacyclovir', 500, '09:00', FALSE, TRUE, 'Prodrug of acyclovir', NOW(), 1),
('Famciclovir', 250, '07:30', FALSE, TRUE, 'Antiviral for herpes infections', NOW(), 1),
('Ganciclovir', 250, '08:30', FALSE, TRUE, 'Antiviral for CMV infections', NOW(), 1),
('Oseltamivir', 75, '10:00', FALSE, TRUE, 'Neuraminidase inhibitor for flu', NOW(), 1),
('Zanamivir', 10, '09:15', FALSE, TRUE, 'Inhaled antiviral for flu', NOW(), 1),
('Ribavirin', 200, '08:45', FALSE, TRUE, 'Broad-spectrum antiviral', NOW(), 1),
('Interferons', 180, '07:00', FALSE, TRUE, 'Immune system proteins', NOW(), 1),
('Lamivudine', 150, '06:30', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Zidovudine', 300, '08:00', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Stavudine', 40, '09:30', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Didanosine', 400, '07:15', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Abacavir', 300, '06:45', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Emtricitabine', 200, '08:15', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Tenofovir', 300, '09:00', FALSE, TRUE, 'Nucleotide reverse transcriptase inhibitor', NOW(), 1),
('Efavirenz', 600, '10:30', FALSE, TRUE, 'Non-nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Nevirapine', 200, '07:00', FALSE, TRUE, 'Non-nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Rilpivirine', 25, '08:00', FALSE, TRUE, 'Non-nucleoside reverse transcriptase inhibitor', NOW(), 1),
('Lopinavir', 200, '09:00', FALSE, TRUE, 'Protease inhibitor', NOW(), 1),
('Ritonavir', 100, '10:00', FALSE, TRUE, 'Protease inhibitor', NOW(), 1);

-- Antifungals (20 medications)
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Fluconazole', 150, '08:00', FALSE, TRUE, 'Triazole antifungal', NOW(), 1),
('Itraconazole', 200, '09:00', FALSE, TRUE, 'Triazole antifungal', NOW(), 1),
('Ketoconazole', 200, '07:30', FALSE, TRUE, 'Imidazole antifungal', NOW(), 1),
('Voriconazole', 200, '08:30', FALSE, TRUE, 'Second-generation triazole', NOW(), 1),
('Posaconazole', 300, '10:00', FALSE, TRUE, 'Extended-spectrum triazole', NOW(), 1),
('Amphotericin B', 50, '09:15', FALSE, TRUE, 'Polyene antifungal', NOW(), 1),
('Nystatin', 100000, '08:45', FALSE, TRUE, 'Polyene antifungal', NOW(), 1),
('Griseofulvin', 500, '07:00', FALSE, TRUE, 'Antifungal for dermatophytes', NOW(), 1),
('Terbinafine', 250, '06:30', FALSE, TRUE, 'Allylamine antifungal', NOW(), 1),
('Clotrimazole', 10, '08:00', FALSE, TRUE, 'Topical imidazole antifungal', NOW(), 1),
('Miconazole', 200, '09:30', FALSE, TRUE, 'Imidazole antifungal', NOW(), 1),
('Econazole', 150, '07:15', FALSE, TRUE, 'Imidazole antifungal', NOW(), 1),
('Butoconazole', 100, '06:45', FALSE, TRUE, 'Imidazole antifungal', NOW(), 1),
('Terconazole', 80, '08:15', FALSE, TRUE, 'Triazole antifungal', NOW(), 1),
('Ciclopirox', 80, '09:00', FALSE, TRUE, 'Hydroxypyridone antifungal', NOW(), 1),
('Amorolfine', 50, '10:30', FALSE, TRUE, 'Morpholine antifungal', NOW(), 1),
('Butenafine', 10, '07:00', FALSE, TRUE, 'Benzylamine antifungal', NOW(), 1),
('Tolnaftate', 10, '08:00', FALSE, TRUE, 'Thiocarbamate antifungal', NOW(), 1),
('Undecylenic acid', 25, '09:00', FALSE, TRUE, 'Fatty acid antifungal', NOW(), 1),
('Flucytosine', 250, '10:00', FALSE, TRUE, 'Pyrimidine antifungal', NOW(), 1);

-- Cardiovascular medications (20 medications)
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Atenolol', 50, '08:00', FALSE, TRUE, 'Beta-blocker for hypertension', NOW(), 1),
('Metoprolol', 100, '09:00', FALSE, TRUE, 'Selective beta-blocker', NOW(), 1),
('Propranolol', 40, '07:30', FALSE, TRUE, 'Non-selective beta-blocker', NOW(), 1),
('Lisinopril', 20, '08:30', FALSE, TRUE, 'ACE inhibitor', NOW(), 1),
('Enalapril', 10, '10:00', FALSE, TRUE, 'ACE inhibitor', NOW(), 1),
('Captopril', 25, '09:15', FALSE, TRUE, 'ACE inhibitor', NOW(), 1),
('Losartan', 50, '08:45', FALSE, TRUE, 'Angiotensin receptor blocker', NOW(), 1),
('Valsartan', 80, '07:00', FALSE, TRUE, 'Angiotensin receptor blocker', NOW(), 1),
('Amlodipine', 5, '06:30', FALSE, TRUE, 'Calcium channel blocker', NOW(), 1),
('Nifedipine', 30, '08:00', FALSE, TRUE, 'Calcium channel blocker', NOW(), 1),
('Diltiazem', 120, '09:30', FALSE, TRUE, 'Non-dihydropyridine calcium channel blocker', NOW(), 1),
('Verapamil', 80, '07:15', FALSE, TRUE, 'Non-dihydropyridine calcium channel blocker', NOW(), 1),
('Hydrochlorothiazide', 25, '06:45', FALSE, TRUE, 'Thiazide diuretic', NOW(), 1),
('Furosemide', 40, '08:15', FALSE, TRUE, 'Loop diuretic', NOW(), 1),
('Spironolactone', 50, '09:00', FALSE, TRUE, 'Potassium-sparing diuretic', NOW(), 1),
('Digoxin', 0.25, '10:30', FALSE, TRUE, 'Cardiac glycoside', NOW(), 1),
('Warfarin', 5, '07:00', FALSE, TRUE, 'Anticoagulant', NOW(), 1),
('Clopidogrel', 75, '08:00', FALSE, TRUE, 'Antiplatelet agent', NOW(), 1),
('Atorvastatin', 20, '09:00', FALSE, TRUE, 'HMG-CoA reductase inhibitor', NOW(), 1),
('Simvastatin', 20, '10:00', FALSE, TRUE, 'HMG-CoA reductase inhibitor', NOW(), 1);

-- Psychiatric medications (20 medications)
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Fluoxetine', 20, '08:00', FALSE, TRUE, 'SSRI antidepressant', NOW(), 1),
('Sertraline', 50, '09:00', FALSE, TRUE, 'SSRI antidepressant', NOW(), 1),
('Paroxetine', 20, '07:30', FALSE, TRUE, 'SSRI antidepressant', NOW(), 1),
('Citalopram', 20, '08:30', FALSE, TRUE, 'SSRI antidepressant', NOW(), 1),
('Escitalopram', 10, '10:00', FALSE, TRUE, 'SSRI antidepressant', NOW(), 1),
('Venlafaxine', 75, '09:15', FALSE, TRUE, 'SNRI antidepressant', NOW(), 1),
('Duloxetine', 60, '08:45', FALSE, TRUE, 'SNRI antidepressant', NOW(), 1),
('Mirtazapine', 30, '07:00', FALSE, TRUE, 'Tetracyclic antidepressant', NOW(), 1),
('Trazodone', 50, '06:30', FALSE, TRUE, 'Atypical antidepressant', NOW(), 1),
('Lithium', 300, '08:00', FALSE, TRUE, 'Mood stabilizer', NOW(), 1),
('Quetiapine', 100, '09:30', FALSE, TRUE, 'Atypical antipsychotic', NOW(), 1),
('Olanzapine', 10, '07:15', FALSE, TRUE, 'Atypical antipsychotic', NOW(), 1),
('Risperidone', 2, '06:45', FALSE, TRUE, 'Atypical antipsychotic', NOW(), 1),
('Aripiprazole', 10, '08:15', FALSE, TRUE, 'Atypical antipsychotic', NOW(), 1),
('Haloperidol', 5, '09:00', FALSE, TRUE, 'Typical antipsychotic', NOW(), 1),
('Clonazepam', 1, '10:30', FALSE, TRUE, 'Benzodiazepine anxiolytic', NOW(), 1),
('Lorazepam', 1, '07:00', FALSE, TRUE, 'Benzodiazepine anxiolytic', NOW(), 1),
('Diazepam', 5, '08:00', FALSE, TRUE, 'Benzodiazepine anxiolytic', NOW(), 1),
('Alprazolam', 0.5, '09:00', FALSE, TRUE, 'Benzodiazepine anxiolytic', NOW(), 1),
('Bupropion', 150, '10:00', FALSE, TRUE, 'Atypical antidepressant', NOW(), 1);

-- Endocrine medications (20 medications)
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Metformin', 1000, '08:00', FALSE, TRUE, 'Biguanide antidiabetic', NOW(), 1),
('Glipizide', 5, '09:00', FALSE, TRUE, 'Sulfonylurea antidiabetic', NOW(), 1),
('Insulin', 10, '07:30', FALSE, TRUE, 'Hormone for diabetes', NOW(), 1),
('Levothyroxine', 100, '08:30', FALSE, TRUE, 'Thyroid hormone replacement', NOW(), 1),
('Methimazole', 10, '10:00', FALSE, TRUE, 'Antithyroid medication', NOW(), 1),
('Propylthiouracil', 50, '09:15', FALSE, TRUE, 'Antithyroid medication', NOW(), 1),
('Prednisone', 10, '08:45', FALSE, TRUE, 'Corticosteroid', NOW(), 1),
('Prednisolone', 10, '07:00', FALSE, TRUE, 'Corticosteroid', NOW(), 1),
('Hydrocortisone', 20, '06:30', FALSE, TRUE, 'Corticosteroid', NOW(), 1),
('Dexamethasone', 2, '08:00', FALSE, TRUE, 'Potent corticosteroid', NOW(), 1),
('Fludrocortisone', 0.1, '09:30', FALSE, TRUE, 'Mineralocorticoid', NOW(), 1),
('Testosterone', 200, '07:15', FALSE, TRUE, 'Male hormone replacement', NOW(), 1),
('Estrogen', 1, '06:45', FALSE, TRUE, 'Female hormone replacement', NOW(), 1),
('Progesterone', 100, '08:15', FALSE, TRUE, 'Female hormone', NOW(), 1),
('Calcitriol', 0.25, '09:00', FALSE, TRUE, 'Active form of vitamin D', NOW(), 1),
('Glimepiride', 2, '10:30', FALSE, TRUE, 'Sulfonylurea antidiabetic', NOW(), 1),
('Pioglitazone', 30, '07:00', FALSE, TRUE, 'Thiazolidinedione antidiabetic', NOW(), 1),
('Sitagliptin', 100, '08:00', FALSE, TRUE, 'DPP-4 inhibitor', NOW(), 1),
('Liraglutide', 1.2, '09:00', FALSE, TRUE, 'GLP-1 receptor agonist', NOW(), 1),
('Empagliflozin', 10, '10:00', FALSE, TRUE, 'SGLT2 inhibitor', NOW(), 1);

-- =========================================
-- Insert same medications for users 2-7
-- =========================================

-- Anti-inflammatories for users 2-7
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Ibuprofen', 400, '08:00', FALSE, TRUE, 'Anti-inflammatory and pain reliever', NOW(), 2),
('Naproxen', 500, '09:00', FALSE, TRUE, 'Anti-inflammatory and pain reliever', NOW(), 3),
('Diclofenac', 50, '07:30', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 4),
('Ketoprofen', 100, '08:30', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 5),
('Celecoxib', 200, '10:00', FALSE, TRUE, 'COX-2 selective inhibitor', NOW(), 6),
('Meloxicam', 15, '09:15', FALSE, TRUE, 'Anti-inflammatory for arthritis', NOW(), 7),
('Aspirin', 325, '08:00', FALSE, TRUE, 'Pain reliever and blood thinner', NOW(), 2),
('Indomethacin', 25, '08:45', FALSE, TRUE, 'Strong anti-inflammatory', NOW(), 3),
('Piroxicam', 20, '07:00', FALSE, TRUE, 'Long-acting anti-inflammatory', NOW(), 4),
('Sulindac', 150, '08:15', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 5),
('Tolmetin', 400, '09:00', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 6),
('Etodolac', 300, '10:30', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 7),
('Fenoprofen', 300, '08:00', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 2),
('Flurbiprofen', 100, '09:00', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 3),
('Meclofenamate', 100, '07:15', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 4),
('Oxaprozin', 600, '08:00', FALSE, TRUE, 'Long-acting anti-inflammatory', NOW(), 5),
('Ketorolac', 10, '06:45', FALSE, TRUE, 'Strong pain reliever', NOW(), 6),
('Nimesulide', 100, '09:30', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 7),
('Aceclofenac', 100, '08:00', FALSE, TRUE, 'Anti-inflammatory drug', NOW(), 2),
('Lornoxicam', 8, '09:00', FALSE, TRUE, 'Anti-inflammatory medication', NOW(), 3);

-- Analgesics for users 2-7
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Paracetamol', 500, '08:00', FALSE, TRUE, 'Pain reliever and fever reducer', NOW(), 4),
('Tramadol', 50, '07:30', FALSE, TRUE, 'Moderate to strong pain reliever', NOW(), 5),
('Codeine', 30, '08:30', FALSE, TRUE, 'Opioid pain medication', NOW(), 6),
('Oxycodone', 10, '10:00', FALSE, TRUE, 'Strong opioid pain medication', NOW(), 7),
('Fentanyl', 25, '09:15', FALSE, TRUE, 'Powerful opioid pain medication', NOW(), 2),
('Morphine', 15, '08:45', FALSE, TRUE, 'Strong opioid pain reliever', NOW(), 3),
('Hydrocodone', 10, '07:00', FALSE, TRUE, 'Opioid pain medication', NOW(), 4),
('Methadone', 10, '06:30', FALSE, TRUE, 'Long-acting opioid', NOW(), 5),
('Pethidine', 50, '08:00', FALSE, TRUE, 'Opioid pain medication', NOW(), 6),
('Buprenorphine', 5, '09:30', FALSE, TRUE, 'Partial opioid agonist', NOW(), 7),
('Pentazocine', 50, '07:15', FALSE, TRUE, 'Mixed opioid agonist', NOW(), 2),
('Nalbuphine', 10, '06:45', FALSE, TRUE, 'Mixed opioid agonist', NOW(), 3),
('Butorphanol', 2, '08:15', FALSE, TRUE, 'Mixed opioid agonist', NOW(), 4),
('Dextropropoxyphene', 100, '09:00', FALSE, TRUE, 'Mild opioid pain reliever', NOW(), 5),
('Hydromorphone', 2, '10:30', FALSE, TRUE, 'Strong opioid pain medication', NOW(), 6),
('Oxymorphone', 5, '07:00', FALSE, TRUE, 'Strong opioid pain medication', NOW(), 7),
('Tapentadol', 50, '08:00', FALSE, TRUE, 'Centrally acting analgesic', NOW(), 2),
('Pregabalin', 150, '09:00', FALSE, TRUE, 'Neuropathic pain medication', NOW(), 3),
('Gabapentin', 300, '10:00', FALSE, TRUE, 'Neuropathic pain medication', NOW(), 4),
('Duloxetine', 60, '08:00', FALSE, TRUE, 'Antidepressant for chronic pain', NOW(), 5);

-- Antibiotics for users 2-7
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Amoxicillin', 500, '08:00', FALSE, TRUE, 'Broad-spectrum antibiotic', NOW(), 6),
('Cephalexin', 500, '09:00', FALSE, TRUE, 'First-generation cephalosporin', NOW(), 7),
('Azithromycin', 250, '07:30', FALSE, TRUE, 'Macrolide antibiotic', NOW(), 2),
('Clarithromycin', 500, '08:30', FALSE, TRUE, 'Macrolide antibiotic', NOW(), 3),
('Doxycycline', 100, '10:00', FALSE, TRUE, 'Tetracycline antibiotic', NOW(), 4),
('Ciprofloxacin', 500, '09:15', FALSE, TRUE, 'Fluoroquinolone antibiotic', NOW(), 5),
('Levofloxacin', 500, '08:45', FALSE, TRUE, 'Fluoroquinolone antibiotic', NOW(), 6),
('Moxifloxacin', 400, '07:00', FALSE, TRUE, 'Fourth-generation fluoroquinolone', NOW(), 7),
('Penicillin', 500, '06:30', FALSE, TRUE, 'Beta-lactam antibiotic', NOW(), 2),
('Ampicillin', 500, '08:00', FALSE, TRUE, 'Broad-spectrum penicillin', NOW(), 3),
('Clindamycin', 300, '09:30', FALSE, TRUE, 'Lincosamide antibiotic', NOW(), 4),
('Erythromycin', 500, '07:15', FALSE, TRUE, 'Macrolide antibiotic', NOW(), 5),
('Tetracycline', 500, '06:45', FALSE, TRUE, 'Broad-spectrum antibiotic', NOW(), 6),
('Minocycline', 100, '08:15', FALSE, TRUE, 'Second-generation tetracycline', NOW(), 7),
('Ofloxacin', 400, '09:00', FALSE, TRUE, 'Second-generation fluoroquinolone', NOW(), 2),
('Norfloxacin', 400, '10:30', FALSE, TRUE, 'Second-generation fluoroquinolone', NOW(), 3),
('Trimethoprim', 160, '07:00', FALSE, TRUE, 'Antifolate antibiotic', NOW(), 4),
('Sulfamethoxazole', 800, '08:00', FALSE, TRUE, 'Sulfonamide antibiotic', NOW(), 5),
('Metronidazole', 500, '09:00', FALSE, TRUE, 'Antiprotozoal and antibacterial', NOW(), 6),
('Vancomycin', 1000, '10:00', FALSE, TRUE, 'Glycopeptide antibiotic', NOW(), 7);

-- Antivirals for users 2-7
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Acyclovir', 400, '08:00', FALSE, TRUE, 'Antiviral for herpes infections', NOW(), 2),
('Valacyclovir', 500, '09:00', FALSE, TRUE, 'Prodrug of acyclovir', NOW(), 3),
('Famciclovir', 250, '07:30', FALSE, TRUE, 'Antiviral for herpes infections', NOW(), 4),
('Ganciclovir', 250, '08:30', FALSE, TRUE, 'Antiviral for CMV infections', NOW(), 5),
('Oseltamivir', 75, '10:00', FALSE, TRUE, 'Neuraminidase inhibitor for flu', NOW(), 6),
('Zanamivir', 10, '09:15', FALSE, TRUE, 'Inhaled antiviral for flu', NOW(), 7),
('Ribavirin', 200, '08:45', FALSE, TRUE, 'Broad-spectrum antiviral', NOW(), 2),
('Interferons', 180, '07:00', FALSE, TRUE, 'Immune system proteins', NOW(), 3),
('Lamivudine', 150, '06:30', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 4),
('Zidovudine', 300, '08:00', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 5),
('Stavudine', 40, '09:30', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 6),
('Didanosine', 400, '07:15', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 7),
('Abacavir', 300, '06:45', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 2),
('Emtricitabine', 200, '08:15', FALSE, TRUE, 'Nucleoside reverse transcriptase inhibitor', NOW(), 3),
('Tenofovir', 300, '09:00', FALSE, TRUE, 'Nucleotide reverse transcriptase inhibitor', NOW(), 4),
('Efavirenz', 600, '10:30', FALSE, TRUE, 'Non-nucleoside reverse transcriptase inhibitor', NOW(), 5),
('Nevirapine', 200, '07:00', FALSE, TRUE, 'Non-nucleoside reverse transcriptase inhibitor', NOW(), 6),
('Rilpivirine', 25, '08:00', FALSE, TRUE, 'Non-nucleoside reverse transcriptase inhibitor', NOW(), 7),
('Lopinavir', 200, '09:00', FALSE, TRUE, 'Protease inhibitor', NOW(), 2),
('Ritonavir', 100, '10:00', FALSE, TRUE, 'Protease inhibitor', NOW(), 3);

-- Antifungals for users 2-7
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Fluconazole', 150, '08:00', FALSE, TRUE, 'Triazole antifungal', NOW(), 4),
('Itraconazole', 200, '09:00', FALSE, TRUE, 'Triazole antifungal', NOW(), 5),
('Ketoconazole', 200, '07:30', FALSE, TRUE, 'Imidazole antifungal', NOW(), 6),
('Voriconazole', 200, '08:30', FALSE, TRUE, 'Second-generation triazole', NOW(), 7),
('Posaconazole', 300, '10:00', FALSE, TRUE, 'Extended-spectrum triazole', NOW(), 2),
('Amphotericin B', 50, '09:15', FALSE, TRUE, 'Polyene antifungal', NOW(), 3),
('Nystatin', 100000, '08:45', FALSE, TRUE, 'Polyene antifungal', NOW(), 4),
('Griseofulvin', 500, '07:00', FALSE, TRUE, 'Antifungal for dermatophytes', NOW(), 5),
('Terbinafine', 250, '06:30', FALSE, TRUE, 'Allylamine antifungal', NOW(), 6),
('Clotrimazole', 10, '08:00', FALSE, TRUE, 'Topical imidazole antifungal', NOW(), 7),
('Miconazole', 200, '09:30', FALSE, TRUE, 'Imidazole antifungal', NOW(), 2),
('Econazole', 150, '07:15', FALSE, TRUE, 'Imidazole antifungal', NOW(), 3),
('Butoconazole', 100, '06:45', FALSE, TRUE, 'Imidazole antifungal', NOW(), 4),
('Terconazole', 80, '08:15', FALSE, TRUE, 'Triazole antifungal', NOW(), 5),
('Ciclopirox', 80, '09:00', FALSE, TRUE, 'Hydroxypyridone antifungal', NOW(), 6),
('Amorolfine', 50, '10:30', FALSE, TRUE, 'Morpholine antifungal', NOW(), 7),
('Butenafine', 10, '07:00', FALSE, TRUE, 'Benzylamine antifungal', NOW(), 2),
('Tolnaftate', 10, '08:00', FALSE, TRUE, 'Thiocarbamate antifungal', NOW(), 3),
('Undecylenic acid', 25, '09:00', FALSE, TRUE, 'Fatty acid antifungal', NOW(), 4),
('Flucytosine', 250, '10:00', FALSE, TRUE, 'Pyrimidine antifungal', NOW(), 5);

-- Cardiovascular medications for users 2-7
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Atenolol', 50, '08:00', FALSE, TRUE, 'Beta-blocker for hypertension', NOW(), 6),
('Metoprolol', 100, '09:00', FALSE, TRUE, 'Selective beta-blocker', NOW(), 7),
('Propranolol', 40, '07:30', FALSE, TRUE, 'Non-selective beta-blocker', NOW(), 2),
('Lisinopril', 20, '08:30', FALSE, TRUE, 'ACE inhibitor', NOW(), 3),
('Enalapril', 10, '10:00', FALSE, TRUE, 'ACE inhibitor', NOW(), 4),
('Captopril', 25, '09:15', FALSE, TRUE, 'ACE inhibitor', NOW(), 5),
('Losartan', 50, '08:45', FALSE, TRUE, 'Angiotensin receptor blocker', NOW(), 6),
('Valsartan', 80, '07:00', FALSE, TRUE, 'Angiotensin receptor blocker', NOW(), 7),
('Amlodipine', 5, '06:30', FALSE, TRUE, 'Calcium channel blocker', NOW(), 2),
('Nifedipine', 30, '08:00', FALSE, TRUE, 'Calcium channel blocker', NOW(), 3),
('Diltiazem', 120, '09:30', FALSE, TRUE, 'Non-dihydropyridine calcium channel blocker', NOW(), 4),
('Verapamil', 80, '07:15', FALSE, TRUE, 'Non-dihydropyridine calcium channel blocker', NOW(), 5),
('Hydrochlorothiazide', 25, '06:45', FALSE, TRUE, 'Thiazide diuretic', NOW(), 6),
('Furosemide', 40, '08:15', FALSE, TRUE, 'Loop diuretic', NOW(), 7),
('Spironolactone', 50, '09:00', FALSE, TRUE, 'Potassium-sparing diuretic', NOW(), 2),
('Digoxin', 0.25, '10:30', FALSE, TRUE, 'Cardiac glycoside', NOW(), 3),
('Warfarin', 5, '07:00', FALSE, TRUE, 'Anticoagulant', NOW(), 4),
('Clopidogrel', 75, '08:00', FALSE, TRUE, 'Antiplatelet agent', NOW(), 5),
('Atorvastatin', 20, '09:00', FALSE, TRUE, 'HMG-CoA reductase inhibitor', NOW(), 6),
('Simvastatin', 20, '10:00', FALSE, TRUE, 'HMG-CoA reductase inhibitor', NOW(), 7);

-- Psychiatric medications for users 2-7
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Fluoxetine', 20, '08:00', FALSE, TRUE, 'SSRI antidepressant', NOW(), 2),
('Sertraline', 50, '09:00', FALSE, TRUE, 'SSRI antidepressant', NOW(), 3),
('Paroxetine', 20, '07:30', FALSE, TRUE, 'SSRI antidepressant', NOW(), 4),
('Citalopram', 20, '08:30', FALSE, TRUE, 'SSRI antidepressant', NOW(), 5),
('Escitalopram', 10, '10:00', FALSE, TRUE, 'SSRI antidepressant', NOW(), 6),
('Venlafaxine', 75, '09:15', FALSE, TRUE, 'SNRI antidepressant', NOW(), 7),
('Duloxetine', 60, '08:45', FALSE, TRUE, 'SNRI antidepressant', NOW(), 2),
('Mirtazapine', 30, '07:00', FALSE, TRUE, 'Tetracyclic antidepressant', NOW(), 3),
('Trazodone', 50, '06:30', FALSE, TRUE, 'Atypical antidepressant', NOW(), 4),
('Lithium', 300, '08:00', FALSE, TRUE, 'Mood stabilizer', NOW(), 5),
('Quetiapine', 100, '09:30', FALSE, TRUE, 'Atypical antipsychotic', NOW(), 6),
('Olanzapine', 10, '07:15', FALSE, TRUE, 'Atypical antipsychotic', NOW(), 7),
('Risperidone', 2, '06:45', FALSE, TRUE, 'Atypical antipsychotic', NOW(), 2),
('Aripiprazole', 10, '08:15', FALSE, TRUE, 'Atypical antipsychotic', NOW(), 3),
('Haloperidol', 5, '09:00', FALSE, TRUE, 'Typical antipsychotic', NOW(), 4),
('Clonazepam', 1, '10:30', FALSE, TRUE, 'Benzodiazepine anxiolytic', NOW(), 5),
('Lorazepam', 1, '07:00', FALSE, TRUE, 'Benzodiazepine anxiolytic', NOW(), 6),
('Diazepam', 5, '08:00', FALSE, TRUE, 'Benzodiazepine anxiolytic', NOW(), 7),
('Alprazolam', 0.5, '09:00', FALSE, TRUE, 'Benzodiazepine anxiolytic', NOW(), 2),
('Bupropion', 150, '10:00', FALSE, TRUE, 'Atypical antidepressant', NOW(), 3);

-- Endocrine medications for users 2-7
INSERT INTO medications (name, dose, hour, taken, active, description, created_at, user_id) VALUES
('Metformin', 1000, '08:00', FALSE, TRUE, 'Biguanide antidiabetic', NOW(), 4),
('Glipizide', 5, '09:00', FALSE, TRUE, 'Sulfonylurea antidiabetic', NOW(), 5),
('Insulin', 10, '07:30', FALSE, TRUE, 'Hormone for diabetes', NOW(), 6),
('Levothyroxine', 100, '08:30', FALSE, TRUE, 'Thyroid hormone replacement', NOW(), 7),
('Methimazole', 10, '10:00', FALSE, TRUE, 'Antithyroid medication', NOW(), 2),
('Propylthiouracil', 50, '09:15', FALSE, TRUE, 'Antithyroid medication', NOW(), 3),
('Prednisone', 10, '08:45', FALSE, TRUE, 'Corticosteroid', NOW(), 4),
('Prednisolone', 10, '07:00', FALSE, TRUE, 'Corticosteroid', NOW(), 5),
('Hydrocortisone', 20, '06:30', FALSE, TRUE, 'Corticosteroid', NOW(), 6),
('Dexamethasone', 2, '08:00', FALSE, TRUE, 'Potent corticosteroid', NOW(), 7),
('Fludrocortisone', 0.1, '09:30', FALSE, TRUE, 'Mineralocorticoid', NOW(), 2),
('Testosterone', 200, '07:15', FALSE, TRUE, 'Male hormone replacement', NOW(), 3),
('Estrogen', 1, '06:45', FALSE, TRUE, 'Female hormone replacement', NOW(), 4),
('Progesterone', 100, '08:15', FALSE, TRUE, 'Female hormone', NOW(), 5),
('Calcitriol', 0.25, '09:00', FALSE, TRUE, 'Active form of vitamin D', NOW(), 6),
('Glimepiride', 2, '10:30', FALSE, TRUE, 'Sulfonylurea antidiabetic', NOW(), 7),
('Pioglitazone', 30, '07:00', FALSE, TRUE, 'Thiazolidinedione antidiabetic', NOW(), 2),
('Sitagliptin', 100, '08:00', FALSE, TRUE, 'DPP-4 inhibitor', NOW(), 3),
('Liraglutide', 1.2, '09:00', FALSE, TRUE, 'GLP-1 receptor agonist', NOW(), 4),
('Empagliflozin', 10, '10:00', FALSE, TRUE, 'SGLT2 inhibitor', NOW(), 5);