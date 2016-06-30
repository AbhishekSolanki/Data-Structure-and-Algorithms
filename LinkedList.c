//LinkedList Sample Program
#include <stdio.h>
#include <stdlib.h>

struct node{
    int data;
    struct node *next;
};

//Function displaying value
void printList(struct node* n){
    while(n!=NULL){
        printf("%d ",n->data);
        n=n->next;
    }
}

//Function pushing new node in the begining
void push(struct node** head,int data){
	struct node* newNode = NULL;
	newNode = (struct node*)malloc(sizeof(struct node));
	newNode->data=data;
	newNode->next= *head;
	*head = newNode;
}

//Function pushing new node at the end
void pushAtEnd(struct node** head,int data){
	struct node* newNode = NULL;
	newNode = (struct node*)malloc(sizeof(struct node));
	newNode->data=data;
	newNode->next=NULL;
	struct node* last = *head;
	while(last->next!=NULL){
		last = last->next;
	}
	last->next=newNode;
}

int main()
{
    struct node* first = NULL;
    struct node* second = NULL;
    struct node* third = NULL;
    
    first =(struct node*)malloc(sizeof(struct node));
    second=(struct node*)malloc(sizeof(struct node));
    third=(struct node*)malloc(sizeof(struct node));
    
    first->data=1;
    first->next=second;
    
    second->data=2;
    second->next=third;
    
    third->data=3;
    third->next=NULL;
    
	//pushing new node in the begining
	push(&first,0);
	
	//pushing new node at the end
	pushAtEnd(&first,4);
    
	//displaying value
    printList(first);
    return 0;
}
