module lab1_FSM (
    input clk, rst, dollar, fifty, cancel,
    output reg [2:0] st,
    output reg money_return, dispense, insert_coin
);
    parameter INIT=0, S50c=1, VEND=2, RETURN=3;
    reg [2:0] nst, st
    always @* begin
        nst = st; 
        insert_coin = 1; // default state
        money_return = 0;
        dispense = 0;
        case (st)
            INIT: begin
                insert_coin = 1;
                money_return = 0;
                dispense = 0;
                if (fifty)
                    nst = S50c;
                if (dollar)
                    nst = VEND;
            end
            S50c: begin 
                insert_coin = 1;
                money_return = 0;
                dispense = 0;
                if (fifty)
                    nst = VEND;
                if (dollar || cancel)
                    nst = RETURN;
            end
            VEND: begin
                insert_coin = 0;
                money_return = 0;
                dispense = 1;
                nst = INIT;
            end
            RETURN: begin
                insert_coin = 0;
                money_return = 1;
                dispense = 0;
                nst = INIT;
            end
        endcase
    end
    always @ (posedge clk) begin
        if (rst)
            st <= INIT; // Reset
        else
            st <= nst;
    end
endmodule