package br.com.hellmets.motocerol.AndGraph;

import br.com.hellmets.motocerol.R;

/**
 * Created by breno on 12/12/2017.
 */

public class CenaGanhou extends AGScene
{
    AGSprite ganhou = null;

    //Construtor da classe
    public CenaGanhou(AGGameManager manager)
    {
        super(manager);
    }

    @Override
    public void init()
    {
        ganhou = this.createSprite(R.mipmap.imagem_vencedor, 1,1);
        ganhou.setScreenPercent(30,30);

        ganhou.vrPosition.setXY(AGScreenManager.iScreenWidth/2, AGScreenManager.iScreenHeight/2);

        //Seta a cor de fundo para VERDE
        setSceneBackgroundColor(0,0.75f,0);
    }

    @Override
    public void restart()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public void loop()
    {
        //Método de volta para o menu principal do jogo
        //Ao clicar no botão de voltar do celular, volta para a cena de menu
        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            //Seleciona a cena de MENU
            this.vrGameManager.setCurrentScene(0);
            return;
        }
    }
}
