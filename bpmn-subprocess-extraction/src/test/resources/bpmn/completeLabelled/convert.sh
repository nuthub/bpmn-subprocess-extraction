#!/bin/sh

sed \
    -e 's/complete2labelled/complete1labelled/g' \
    -e 's/anfertigen/erstellen/g' \
    -e 's/Artikel/Ware/g' \
    -e 's/id="FormalExpression_1">B<\/bpmn2/id="FormalExpression_1">A<\/bpmn2/g' \
    -e 's/id="FormalExpression_2">A<\/bpmn2/id="FormalExpression_2">B<\/bpmn2/g' \
    < complete2labelled.bpmn > complete1labelled.bpmn
echo "complete1labelled.bpmn created";

sed \
    -e 's/complete2labelled/complete2/g' \
    -e 's/Anspruch pruefen/T1/g' \
    -e 's/Lieferschein anfertigen/T2/g' \
    -e 's/Rechnung anfertigen/T3/g' \
    -e 's/Artikel verpacken/T4/g' \
    -e 's/Betrag erstatten/T5/g' \
    -e 's/Gutschein ausstellen/T6/g' \
    < complete2labelled.bpmn > ../complete/complete2.bpmn
echo "complete2.bpmn created";

sed \
    -e 's/complete1labelled/Process/g' \
    -e 's/Anspruch pruefen/T1/g' \
    -e 's/Lieferschein erstellen/T2/g' \
    -e 's/Rechnung erstellen/T3/g' \
    -e 's/Ware verpacken/T4/g' \
    -e 's/Betrag erstatten/T5/g' \
    -e 's/Gutschein ausstellen/T6/g' \
    < complete1labelled.bpmn > ../complete/complete1.bpmn
echo "complete1.bpmn created";

echo "Now remove g9 / g10 and reroute sequence flows in complete1labelled.bpmn (and perhaps complete1.bpmn)"
