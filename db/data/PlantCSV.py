import csv

datain = csv.reader(open('ABSP-plants.csv'), delimiter=',', quotechar='|')

# put CSV data into a list
lst = []
for row in datain:
    lst.append(row)


# separate scientific name (genus and species)
scinames = []
for sci in lst:
    scinames.append(sci[4])
    tempname = scinames.pop()
    tempname = tempname.split(' ', 2)
    scinames.append(tempname)


# prep variables
famid = {}
genusid = {}
fkey = -1
gkey = -1
family_string = ""
genus_string = ""


# for every element in the list
for i, el in enumerate(lst):    
    el[4] = scinames[i][0]
    el.insert(5, scinames[i][1])
    # add family id
    if el[3] in famid:
        el.insert(10, famid.get(el[3]))
    else:
        fkey += 1
        famid[el[3]] = fkey
        el.insert(10, fkey)
        family_string += el[3] + "," + str(fkey) + "\n"
    # add genus id
    if el[4] in genusid:
        el.insert(11, genusid.get(el[4]))
    else:
        gkey += 1
        genusid[el[4]] = gkey
        el.insert(11, gkey) 
        genus_string += el[4] + "," + str(gkey) + "," + str(el[10]) + "\n"

# replace heading names
lst[0].insert(10, "fam_id")
lst[0].insert(11, "genus_id")
lst[0][4] = "Genus"
lst[0][5] = "Species"
lst[0][6] = "Common name"
del lst[0]


# create a string with CSV data for SUBJECT
list_str = ""
for el in lst:
    list_str += str(el[5]) + "," + \
            str(el[6]) + "," + \
            str(el[7]) + "," + \
            str(el[8]) + "," + \
            str(el[9]) + "," + \
            str(el[11]) + "\n"

# write SUBJECT data to file
dataout = open('dataleaf_subject.csv', 'w')
dataout.writelines(list_str)
dataout.close()
print("dataleaf_subject.csv successfully written.")


# write FAMILY data to file
family_string = family_string.replace("Family,0\n", "")
dataout = open('dataleaf_family.csv', 'w')
dataout.writelines(family_string)
dataout.close()
print("dataleaf_family.csv successfully written.")


# write GENUS data to file
genus_string = genus_string.replace("Scientific,0,0\n", "")
dataout = open('dataleaf_genus.csv', 'w')
dataout.writelines(genus_string)
dataout.close()
print("dataleaf_genus.csv successfully written.")


print("\n")
